package com.example.application;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final List<String> list = new ArrayList<>();
    //private static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListViewCustomAdapter adapter;
    private EditText search;
    private ListView listView;
    String[] name_array = new String[100];
    String[] msg_array = new String[100];
    String[] num_array = new String[100];
    private boolean lock_short_touch;



    public BlankFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(String param1, String param2) {
        BlankFragment1 fragment = new BlankFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_blank1, null);

        listView = (ListView) view.findViewById(R.id.lv_main);



        lock_short_touch = false;
        //for menu


        adapter = new ListViewCustomAdapter();
        AssetManager assetManager = getActivity().getAssets();
        try{
            InputStream is = assetManager.open("jsons/test.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);
                String name = jo.getString("name");
                String msg = jo.getString("msg");
                String occ = jo.getString("occ");
                name_array[i] = name;
                msg_array[i] = occ+"\n"+"Phone num :"+msg ;
                num_array[i] = msg;

            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                // adapter.getItem(position)의 return 값은 Object 형
                // 실제 Item의 자료형은 CustomDTO 형이기 때문에
                // 형변환을 시켜야 getResId() 메소드를 호출할 수 있습니다.
                if(!lock_short_touch) {
                    int imgRes = ((ListViewCustomDTO) adapter.getItem(position)).getResId();
                    String title = ((ListViewCustomDTO) adapter.getItem(position)).getTitle();
                    String content = ((ListViewCustomDTO) adapter.getItem(position)).getContent();
                    String number = ((ListViewCustomDTO) adapter.getItem(position)).getNumber();


                    // new Intent(현재 Activity의 Context, 시작할 Activity 클래스)
                    Intent intent = new Intent(view.getContext(), DetailListViewActivity.class);
                    // putExtra(key, value)
                    intent.putExtra("imgRes", imgRes);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    intent.putExtra("number", number);

                    if(!lock_short_touch) startActivity(intent);
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View v, int i, long l) {
                lock_short_touch = true;
                PopupMenu popup = new PopupMenu(view.getContext(), v);
                getActivity().getMenuInflater().inflate(R.menu.menu_listview, popup.getMenu());

                final int index = i;

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.modify:
                                //Toast.makeText(getContext(), "정보 수정", Toast.LENGTH_SHORT).show();

                                int imgRes = ((ListViewCustomDTO) adapter.getItem(index)).getResId();
                                String title = ((ListViewCustomDTO) adapter.getItem(index)).getTitle();
                                String content = ((ListViewCustomDTO) adapter.getItem(index)).getContent();
                                String number = ((ListViewCustomDTO) adapter.getItem(index)).getNumber();

                                // new Intent(현재 Activity의 Context, 시작할 Activity 클래스)
                                Intent intent = new Intent(getContext(), ModifyListViewActivity.class);
                                // putExtra(key, value)
                                intent.putExtra("imgRes", imgRes);
                                intent.putExtra("title", title);
                                intent.putExtra("content", content);
                                intent.putExtra("number", number);
                                intent.putExtra("index",index);

                                startActivityForResult(intent, 0);


                                break;

                            case R.id.delete:
                                ListViewCustomDTO del_dto = (ListViewCustomDTO) adapter.getItem(index);

                                adapter.delItem(del_dto);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(),"Deleted!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        lock_short_touch = false;
                        return false;
                    }


                });

                popup.show();
                return false;
            }
        });

        setData();
        listView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                ListViewCustomDTO mod_dto = (ListViewCustomDTO) data.getSerializableExtra("dto");
                int index = (int) data.getIntExtra("index",0);
                adapter.modifyItem(index, mod_dto);
                adapter.notifyDataSetChanged();
                break;
        }
    }



    private void setData(){
        TypedArray arrResId = getResources().obtainTypedArray(R.array.resId);
        String[] titles = name_array;
        String[] contents = msg_array;
        String[] numbers = num_array;

        for (int i = 0; i < arrResId.length(); i++) {
            ListViewCustomDTO dto = new ListViewCustomDTO();
            dto.setResId(arrResId.getResourceId(i, 0));
            dto.setTitle(titles[i]);
            dto.setContent(contents[i]);
            dto.setNumber(numbers[i]);
            adapter.addItem(dto);

        }

    }



}