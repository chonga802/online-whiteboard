/*
* @author  Oleg Varaksin (ovaraksin@googlemail.com)
* $$Id$$
*/

package com.googlecode.whiteboard.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.googlecode.whiteboard.model.base.AbstractElement;
import com.googlecode.whiteboard.model.transfer.ClientAction;

/*
* Singleton instance of Gson {@link http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html}.
* 
 * @author ova / last modified by $Author$
 * @version $Revision$ 
*/
public class JsonConverter
{
    private static final JsonConverter INSTANCE = new JsonConverter();
    private Gson gson;

    private JsonConverter() {
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(AbstractElement.class, new AbstractElementAdapter());
        gsonBilder.registerTypeAdapter(ClientAction.class, new ClientActionEnumAdapter());
        gsonBilder.serializeNulls();
        gson = gsonBilder.create();
    }

    public static Gson getGson() {
        return INSTANCE.gson;
    }
}
