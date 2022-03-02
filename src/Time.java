public class Time {
    private int Hours = 0;
    private int Minutes = 0;

    public Time(int hours, int minutes) {
        this.Hours = hours;
        this.Minutes = minutes;
    }


    public void add(int minutes) {
        this.add_update(minutes);
    }


    String show() {
        return String.format("%02d", this.Hours) + String.format(":%02d", this.Minutes);
    }


    public boolean is_morning() {
        return 6 <= Hours && Hours < 12;
    }


    public boolean is_day() {
        return 12 <= Hours && Hours < 18;
    }

    public boolean is_evening() {
        return 18 <= Hours && Hours < 23;
    }


    public boolean is_night() {
        return 23 <= Hours || Hours < 6;
    }


    String say_hello() {
        if (this.is_morning()) {
            return "Доброе утро";
        } else if (this.is_day()) {
            return "Добрый день";
        } else if (this.is_evening()) {
            return "Добрый вечер";
        } else if (this.is_night()) {
            return "Доброй ночи";
        } else return "Добрый";
    }


    public void add_update(int minutes) {
        this.Minutes += minutes;
        this.Hours += this.Minutes / 60;
        this.Minutes = this.Minutes % 60;
    }
}
