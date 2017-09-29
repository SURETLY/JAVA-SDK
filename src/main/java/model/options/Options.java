package model.options;

import com.google.gson.JsonObject;

public class Options {
    private int min_term;
    private int max_term;
    private int min_sum;
    private int max_sum;
    private int server_time;


    public int getMin_term() {
        return min_term;
    }

    public int getMax_term() {
        return max_term;
    }

    public int getMax_sum() {
        return max_sum;
    }

    public int getServer_time() {
        return server_time;
    }

    public int getMin_sum() {
        return min_sum;
    }

    public Options() {
    }

    public Options(int min_term, int max_term, int min_sum, int max_sum, int server_time) {
        this.min_term = min_term;
        this.max_term = max_term;
        this.min_sum = min_sum;
        this.max_sum = max_sum;
        this.server_time = server_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Options options = (Options) o;

        if (min_term != options.min_term) return false;
        if (max_term != options.max_term) return false;
        if (min_sum != options.min_sum) return false;
        if (max_sum != options.max_sum) return false;
        return server_time == options.server_time;
    }

    @Override
    public int hashCode() {
        int result = min_term;
        result = 31 * result + max_term;
        result = 31 * result + min_sum;
        result = 31 * result + max_sum;
        result = 31 * result + server_time;
        return result;
    }

    public static Options fromJson(JsonObject jsonObject) {
        int min_term = jsonObject.get("min_term").getAsInt();
        int max_term = jsonObject.get("max_term").getAsInt();
        int min_sum = jsonObject.get("min_sum").getAsInt();
        int max_sum = jsonObject.get("max_sum").getAsInt();
        int server_time = jsonObject.get("server_time").getAsInt();

        return new Options(min_term, max_term, min_sum, max_sum, server_time);
    }
}
