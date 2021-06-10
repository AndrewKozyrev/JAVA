import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;

public class WebCrawler extends RecursiveTask<String> {

  private final String currentLink;
  private final int currentLevel;
  private static final HashSet<String> VISITED_URLS = new HashSet<>();

  public WebCrawler(String currentLink)
  {
    this(currentLink, 0);
  }

  public WebCrawler(String currentLink, int currentLevel)
  {
    this.currentLink = currentLink;
    this.currentLevel = currentLevel;
  }

  @Override
  protected String compute()
  {
    var result = new StringBuilder();
    try {
      // pause for 100 ms
      Thread.sleep(100);
      // connect to url
      var connection = Jsoup.connect(currentLink)
          .ignoreContentType(true)
          .execute();
      // get content type
      var contentType = connection.contentType();
      // if not html
      if (!contentType.contains("text/html"))
      {
        // return formatted link
        return formatUrl();
      }
      // get html document
      var doc = connection.parse();
      // get anchor elements
      var anchors = doc.select("a");
      // get absolute urls inside anchor elements
      var absUrls = anchors.eachAttr("abs:href");
      // leave only valid links
      var pages = filterUrls(absUrls);
      // format parent url and save it to string
      result.append(formatUrl());

      /*
        Extract urls starting with domain 1, then 2, 3, etc...
        Example: https://lenta.ru/food - domain 1
                 https://lenta.ru/food/article1 - domain 2
                 https://lenta.ru/life/health/article2 - domain 3
        Repeat until all links are parsed
       */
      for (int i = 1; !pages.isEmpty(); i++)
      {
        // extract child urls whose domain is level i relative to parent url
        var subpages = getSubpages(pages, i);
        // if nothing found
        if (subpages.isEmpty())
        {
          // there might be an error, because urlList is not empty, but
          // urls which remain are not valid
          if (i > 100)
          {
            var message = String.format("Something went wrong with indexing urls."
                + "\nCheck these urls: %s", pages.toString());
            throw new Exception(message);
          }
          // try next domain
          continue;
        }
        // lock global url list
        synchronized (VISITED_URLS)
        {
          // add all parsed urls to global list, to avoid repeating
          VISITED_URLS.addAll(subpages);
        }
        // delete parsed urls from url list
        pages.removeAll(subpages);
        // create task list for children of level i
        var taskList = new ArrayList<WebCrawler>();
        // for each url in subdomain of level i
        for (var url : subpages)
        {
          // create new task, add 1 to it's domain level
          var task = new WebCrawler(url, currentLevel + 1);
          // start new task immedeately
          task.fork();
          // add the task to a list
          taskList.add(task);
        }

        for (var task : taskList)
        {
          // wait for outcome of each task and then append it to this result
          result.append(task.join());
        }

      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return result.toString();
  }

  public String formatUrl()
  {
    if (currentLevel == 0)
    {
      return currentLink;
    }
    // make correct tabulation
    return "\n" + "\t".repeat(Math.max(0, currentLevel)) + currentLink;
  }

  private List<String> getSubpages(Collection<String> urls, int domain)
  {
    return urls.stream()  // for each url
        .filter(url -> {
          var a1 = url.replace(currentLink, ""); // get relative path
          var a2 = a1.chars().filter(ch -> ch == '/').count(); // get level
          if (a2 == 0)
          {
            a2 = 1;
          }
          return a2 == domain; // compare with given domain
        })
        .collect(Collectors.toList());  // return urls with level == domain
  }

  private Set<String> filterUrls(Collection<String> urls)
  {
    return urls.stream()
        .filter(url -> url.contains(currentLink))  // urls from same domain
        .filter(url -> !url.equals(currentLink))  // not parent urls
        .filter(url -> !VISITED_URLS.contains(url))  // should not repeat
        .filter(url -> !url.contains("#"))   // should not contain #
        .collect(Collectors.toSet());
  }
}
