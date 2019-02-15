/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.mrobot.cn.shapetest;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import app.mrobot.cn.R;


public class LevelListActivity extends AppCompatActivity {

    private ImageView levelImg;
    private EditText levelEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);
        levelImg = (ImageView) findViewById(R.id.level_list_img);
        levelEt = (EditText) findViewById(R.id.level_et);
    }

    public void change(View v)
    {
        int iLevel = 0;
        String level = levelEt.getText().toString();
        LevelListDrawable levelListDrawable = (LevelListDrawable) levelImg.getDrawable();
        try {
            iLevel = Integer.valueOf(level);
        } catch (Exception e) {
            iLevel = 0;
        }
        levelListDrawable.setLevel(iLevel);
    }

}
