public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {

        float[] temperatures = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            float temperature = (float) (32 + 8 * Math.random());
            temperatures[i] = temperature;
        }

        return temperatures;
    }

    public static String getReport(float[] temperatureData) {

        StringBuilder report = new StringBuilder("Температуры пациентов:");
        double sum = 0;
        int wellPatients = 0;

        for (float temperature : temperatureData) {
            report.append(" ").append(temperature);
            sum += temperature;
            if (temperature >= 36.2f && temperature <= 36.9f) {
                wellPatients++;
            }
        }

        report.append(String.format("\nСредняя температура: %.1f", sum/temperatureData.length));
        report.append(String.format("\nКоличество здоровых: %d", wellPatients));

        return report.toString();
    }
}
