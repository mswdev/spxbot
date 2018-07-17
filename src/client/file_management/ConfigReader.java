package client.file_management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sphiinx on 3/23/17.
 */
public class ConfigReader {

    /**
     * The url defined as a String in which to fetch the config file.
     */
    private String url = null;

    /**
     * The constructor containing the url in which to fetch the config file.
     *
     * @param url The url in which to fetch the config file.
     */
    public ConfigReader(String url) {
        this.url = url;
    }

    /**
     * Parses the data from the fetched config file.
     *
     * @return The fetched data that was parsed.
     */
    public Map<String, String> parseConfig() {
        final String[] config_page = fetchConfig();
        if (config_page == null)
            return null;

        HashMap<String, String> map = new HashMap<>();
        for (String parameter : config_page) {
            parameter = parameter.replace("param=", "").replace("msg=", "");
            final String[] split_parameter = parameter.split("=", 2);
            if (split_parameter.length == 1)
                map.put(split_parameter[0], "");
            if (split_parameter.length == 2)
                map.put(split_parameter[0], split_parameter[1]);
        }

        return map;
    }

    /**
     * Fetches a String Array containing the config file from Oldschool Runescapes website.
     *
     * @return A String Array containing the config file from Oldschool Runescapes website.
     */
    private String[] fetchConfig() {
        try {
            List<String> config_lines = new ArrayList<>();
            final URL CONFIG_URL = new URL(url);
            try (BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(CONFIG_URL.openStream()))) {

                String config_line;
                while ((config_line = buffered_reader.readLine()) != null)
                    config_lines.add(config_line);

                buffered_reader.close();
                return config_lines.toArray(new String[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

