package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class LanguageUtils {
    private static Map<String, String> translationMap;
    public static String currentLanguage;

    public static void trySetLanguage(String language) {
        String lang;
        try {
            // Sacar el contenido del json que contiene el idioma.
            lang = IOUtils.toString(Objects.requireNonNull(LanguageUtils.class.getClassLoader().getResourceAsStream(String.format("assets/ADJ/lang/%s.json", language))), StandardCharsets.UTF_8);
        }
        catch (NullPointerException | IOException e) {
            return;
        }

        // Serialización y convertir en array para acceder fácilmente con la key correcta.
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> map1 = gson.fromJson(lang, type);

        currentLanguage = language;
        translationMap = map1;
    }

    public static String getTranslation(String translationKey, String defaultMsg) {
        if (translationMap != null) return translationMap.getOrDefault(translationKey, defaultMsg);
        return defaultMsg;
    }
}
