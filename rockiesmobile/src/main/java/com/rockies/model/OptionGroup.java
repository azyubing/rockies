package com.rockies.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyWay on 2016/10/13.
 */
public class OptionGroup extends Option {

    private List<Option> options = new ArrayList<>();

    public OptionGroup(String key, String value) {
        super(key, value);
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void removeOption(Option option) {
        options.remove(option);
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
