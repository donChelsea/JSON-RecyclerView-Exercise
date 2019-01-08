package android.example.com.jsoncolorsexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //instantiations at the top for scope
    private static final String TAG = "JSON ACTIVITY";
    List<Color> colorsList = new ArrayList<>();
    List<Integer> rgbaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ColorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize recyclerView and adapter objects
        // pass colorList to adapter object

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ColorAdapter(colorsList);

        // assign a layout manager and give it application context. assign adapter
        // pass adapter and layout manager to recycler view

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /******* JSON *******/

        // storing the json code into a java string

        String jsonColors = "{\n" +
                "  \"colors\": [\n" +
                "    {\n" +
                "      \"color\": \"black\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [255,255,255,1],\n" +
                "        \"hex\": \"#000\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"white\",\n" +
                "      \"category\": \"value\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [0,0,0,1],\n" +
                "        \"hex\": \"#FFF\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"red\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [255,0,0,1],\n" +
                "        \"hex\": \"#FF0\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"blue\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [0,0,255,1],\n" +
                "        \"hex\": \"#00F\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"yellow\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [255,255,0,1],\n" +
                "        \"hex\": \"#FF0\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"green\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"secondary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [0,255,0,1],\n" +
                "        \"hex\": \"#0F0\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        try {
            JSONObject oldJsonColors = new JSONObject(jsonColors);  // turning it into a json object for java to read
            JSONArray jsonColorsArray = oldJsonColors.getJSONArray("colors");  // grabbing colors array from json object

            // iterating through the array to pull each color property

            for (int i = 0; i < jsonColorsArray.length(); i++) {
                Color color = new Color();
                color.setColor((String) jsonColorsArray.getJSONObject(i).get("color"));
                color.setCategory((String) jsonColorsArray.getJSONObject(i).get("category"));
                color.setType((String) jsonColorsArray.getJSONObject(i).get("type"));

                // code gets its own class since it has two properties and needs two of its own fields

                Code code = new Code();
                    JSONObject codeObj = (JSONObject) jsonColorsArray.getJSONObject(i).get("code");  // same, pulling code from the array
                    JSONArray rgbaArray = codeObj.getJSONArray("rgba");   // turning it into a json object, this time an array

                // iterate through rgba array and adding array items to arraylist

                for (int j = 0; j < rgbaArray.length(); j++) {
                    rgbaList.add(rgbaArray.getInt(j));
                }
                    String hexObj = codeObj.getString("hex");

                    code.setRgba(rgbaList);
                    code.setHex(hexObj);
                    color.setCode(code);
            }

            for (Color col : colorsList) {
                Log.d(TAG, "onCreate: " +
                        "\nName - " + col.getColor() +
                        "\nRole - " + col.getCategory() +
                        "\nAge - " + col.getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
