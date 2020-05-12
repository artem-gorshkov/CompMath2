package itmo.gorshkov.compmath.simpson;

public class Answer {
    public double answer;
    public int partition;
    public double accuracy;

    public Answer(double answer, int partition, double accuracy) {
        this.answer = answer;
        this.partition = partition;
        this.accuracy = accuracy;
    }
}
