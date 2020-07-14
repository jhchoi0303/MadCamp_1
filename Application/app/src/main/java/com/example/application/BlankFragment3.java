package com.example.application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int screenWidth;
    private int screenHeight;

    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowRightX;
    private float arrowRightY;
    private float arrowLeftX;
    private float arrowLeftY;


    private float StarX;
    private float StarY;
    private float MushroomX;
    private float MushroomY;
    private float DevilX;
    private float DevilY;
    private float Devil2X;
    private float Devil2Y;
    private float Devil3X;
    private float Devil3Y;

    private float buttonX;
    private float buttonY;


    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    // Status Check
    private boolean pause_flg = false;





    private Button button;
    private Questions mQuestions = new Questions();
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionsLength = mQuestions.mQuestions.length;
    final Random r = new Random();

    private int curr_r;
    TextView score;

    Button answer1, answer2, answer3, answer4;
    Button arrowUp,arrowDown,arrowRight,arrowLeft;
    Button Mushroom,Devil,Devil2,Devil3,Star;



    public BlankFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment3 newInstance(String param1, String param2) {
        BlankFragment3 fragment = new BlankFragment3();
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
        final View view = inflater.inflate(R.layout.fragment_blank3, null);

        answer1 = view.findViewById(R.id.answer1);
        arrowUp = view.findViewById(R.id.answer1);


        answer2 = view.findViewById(R.id.answer2);
        arrowDown = view.findViewById(R.id.answer2);



        answer3 = view.findViewById(R.id.answer3);
        arrowRight = view.findViewById(R.id.answer3);




        answer4 = view.findViewById(R.id.answer4);
        arrowLeft = view.findViewById(R.id.answer4);


        button = view.findViewById(R.id.button);
        Star = view.findViewById(R.id.Star);
        Mushroom = view.findViewById(R.id.Mushroom );
        Devil = view.findViewById(R.id.Devil);
        Devil2 = view.findViewById(R.id.Devil2);
        Devil3 = view.findViewById(R.id.Devil3);





        ButtonClickListener buttonClickListener = new ButtonClickListener(getActivity());
        button.setOnClickListener(buttonClickListener);





        score = view.findViewById(R.id.score);

        score.setText("Score: " + mScore);

        curr_r = r.nextInt(mQuestionsLength);
        updateQuestion(curr_r, view);


        WindowManager wm = getActivity().getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Move to out of screen.
        arrowUp.setX(-80.0f);
        arrowUp.setY(-80.0f);
        arrowDown.setX(-80.0f);
        arrowDown.setY(screenHeight + 80.0f);
        arrowRight.setX(screenWidth + 80.0f);
        arrowRight.setY(-80.0f);
        arrowLeft.setX(-80.0f);
        arrowLeft.setY(-80.0f);
        button.setX(-80.0f);
        button.setY(screenHeight + 80.0f);

        // Start the timer.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);



        Star.setX(-80.0f);
        Star.setY(-80.0f);
        Mushroom.setX(-80.0f);
        Mushroom.setY(screenHeight + 80.0f);
        Devil.setX(screenWidth + 80.0f);
        Devil.setY(-80.0f);
        Devil2.setX(-80.0f);
        Devil2.setY(-80.0f);
        Devil3.setX(-80.0f);
        Devil3.setY(screenHeight + 80.0f);

        // Start the timer.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos2();
                    }
                });
            }
        }, 0, 25);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos3();
                    }
                });
            }
        }, 0, 10);


        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==4 && answer1.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    Winner(view);
                }

                else{
                    if(answer1.getText() == mAnswer){
                        mScore++;
                        score.setText("Score: " + mScore);
                        Correct();
                        int next_r;
                        while(curr_r == (next_r = r.nextInt(mQuestionsLength)));
                        curr_r = next_r;
                        updateQuestion(curr_r, view);
                    }
                    else{
                        gameOver(view);
                    }
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==4 && answer2.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    Winner(view);
                }

                else{
                    if(answer2.getText() == mAnswer){
                        mScore++;
                        score.setText("Score: " + mScore);
                        Correct();
                        int next_r;
                        while(curr_r == (next_r = r.nextInt(mQuestionsLength)));
                        curr_r = next_r;
                        updateQuestion(curr_r, view);
                    }
                    else{
                        gameOver(view);
                    }
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==4 && answer3.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    Winner(view);
                }

                else{
                    if(answer3.getText() == mAnswer){
                        mScore++;
                        score.setText("Score: " + mScore);
                        Correct();
                        int next_r;
                        while(curr_r == (next_r = r.nextInt(mQuestionsLength)));
                        curr_r = next_r;
                        updateQuestion(curr_r, view);
                    }
                    else{
                        gameOver(view);
                    }
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==4 && answer4.getText() == mAnswer){
                    mScore++;
                    score.setText("Score: " + mScore);
                    Winner(view);
                }

                else{
                    if(answer4.getText() == mAnswer){
                        mScore++;
                        score.setText("Score: " + mScore);
                        Correct();
                        int next_r;
                        while(curr_r == (next_r = r.nextInt(mQuestionsLength)));
                        curr_r = next_r;
                        updateQuestion(curr_r, view);
                    }
                    else{
                        gameOver(view);
                    }
                }
            }
        });

        Mushroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==4){
                    mScore++;
                    score.setText("Score: " + mScore);
                    Winner(view);
                }

                else{
                    mScore++;
                    score.setText("Score: " + mScore);
                    FreePoint();
                }
            }
        });

        Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==4){
                    mScore++;
                    score.setText("Score: " + mScore);
                    Winner(view);
                }

                else{
                    mScore++;
                    score.setText("Score: " + mScore);
                    FreePoint();
                }
            }
        });



        Devil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==0){

                }

                else{
                    mScore--;
                    score.setText("Score: " + mScore);
                    LostPoint();
                }
            }
        });

        Devil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==0){

                }

                else{
                    mScore--;
                    score.setText("Score: " + mScore);
                    LostPoint();
                }
            }
        });

        Devil3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mScore==0){

                }

                else{
                    mScore--;
                    score.setText("Score: " + mScore);
                    LostPoint();
                }
            }
        });











        return view;
    }

    private void updateQuestion(int num, View view){
        TextView question = view.findViewById(R.id.question);
        question.setText(mQuestions.getQuestion(num));

        answer1 = view.findViewById(R.id.answer1);
        answer2 = view.findViewById(R.id.answer2);
        answer3 = view.findViewById(R.id.answer3);
        answer4 = view.findViewById(R.id.answer4);

        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));

        mAnswer = mQuestions.getCorrectAnswer(num);
    }

    private void gameOver(final View v_iew){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage("Game Over! Your score is  "+ mScore + " points.")
                .setCancelable(false)
                .setPositiveButton("START AGAIN!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mScore = 0;
                                score.setText("Score: " + mScore);

                                int next_r;
                                while(curr_r == (next_r = r.nextInt(mQuestionsLength)));
                                curr_r = next_r;
                                updateQuestion(curr_r, v_iew);
                                /*Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);*/
                                //startActivity(new Intent(getActivity(), BlankFragment3.class));
                            }
                        })
                .setNegativeButton("EXIT",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //getActivity().finish();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void Winner(final View v_iew){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage("You WIN! \nPress the button to get a reward! ")
                .setCancelable(false)
                .setPositiveButton("Go Get Reward",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setClass(getActivity(), ServiceActivity.class);
                                startActivity(intent);

                            }
                        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void Correct(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage("Correct!")
                .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void FreePoint(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage("You earned 1 point!")
                .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void LostPoint(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder
                .setMessage("Too bad! You lost 1 point!")
                .setCancelable(true);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void changePos() {
        // Up
        arrowUpY -= 10;
        if (arrowUp.getY() + arrowUp.getHeight() < 0) {
            arrowUpX = (float)Math.floor(Math.random() * (screenWidth - arrowUp.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);

        // Down
        arrowDownY += 10;
        if (arrowDown.getY() > screenHeight) {
            arrowDownX = (float)Math.floor(Math.random() * (screenWidth - arrowDown.getWidth()));
            arrowDownY = -100.0f;
        }
        arrowDown.setX(arrowDownX);
        arrowDown.setY(arrowDownY);

        // Right
        arrowRightX += 10;
        if (arrowRight.getX() > screenWidth) {
            arrowRightX = -100.0f;
            arrowRightY = (float)Math.floor(Math.random() * (screenHeight - arrowRight.getHeight()));
        }
        arrowRight.setX(arrowRightX);
        arrowRight.setY(arrowRightY);

        // Left
        arrowLeftX -= 10;
        if (arrowLeft.getX() + arrowLeft.getWidth() < 0) {
            arrowLeftX = screenWidth + 100.0f;
            arrowLeftY = (float)Math.floor(Math.random() * (screenHeight - arrowLeft.getHeight()));
        }
        arrowLeft.setX(arrowLeftX);
        arrowLeft.setY(arrowLeftY);


        buttonX -= 10;
        if (button.getX() + button.getWidth() < 0) {
            buttonX = screenWidth + 100.0f;
            buttonY = (float)Math.floor(Math.random() * (screenHeight - button.getHeight()));
        }
        button.setX(buttonX);
        button.setY(buttonY);





    }

    public void changePos2() {
        // Up
        StarY -= 10;
        if (Star.getY() + Star.getHeight() < 0) {
            StarX = (float)Math.floor(Math.random() * (screenWidth - Star.getWidth()));
            StarY = screenHeight + 100.0f;
        }
        Star.setX(StarX);
        Star.setY(StarY);

        // Down
        DevilX -= 10;
        if (Devil.getX() + Devil.getWidth() < 0) {
            DevilX = screenWidth + 100.0f;
            DevilY = (float)Math.floor(Math.random() * (screenHeight - Devil.getHeight()));
        }
        Devil.setX(DevilX);
        Devil.setY(DevilY);

        // Right
        Devil2Y += 10;
        if (Devil2.getX() > screenWidth) {
            Devil2X = -100.0f;
            Devil2Y = (float)Math.floor(Math.random() * (screenHeight - Devil2.getHeight()));
        }
        Devil2.setX(Devil2X);
        Devil2.setY(Devil2Y);

        // Left
        MushroomX -= 10;
        if (Mushroom.getX() + Mushroom.getWidth() < 0) {
            MushroomX = screenWidth + 100.0f;
            MushroomY = (float)Math.floor(Math.random() * (screenHeight - Mushroom.getHeight()));
        }
        Mushroom.setX(MushroomX);
        Mushroom.setY(MushroomY);

        Devil3Y += 10;
        if ( Devil3.getY() > screenHeight) {
            Devil3X = (float)Math.floor(Math.random() * (screenWidth -  Devil3.getWidth()));
            Devil3Y = -100.0f;
        }
        Devil3.setX( Devil3X);
        Devil3.setY( Devil3Y);





    }

    public void changePos3() {
        // Up
        StarY -= 10;
        if (Star.getY() + Star.getHeight() < 0) {
            StarX = (float)Math.floor(Math.random() * (screenWidth - Star.getWidth()));
            StarY = screenHeight + 100.0f;
        }
        Star.setX(StarX);
        Star.setY(StarY);


        MushroomX -= 10;
        if (Mushroom.getX() + Mushroom.getWidth() < 0) {
            MushroomX = screenWidth + 100.0f;
            MushroomY = (float)Math.floor(Math.random() * (screenHeight - Mushroom.getHeight()));
        }
        Mushroom.setX(MushroomX);
        Mushroom.setY(MushroomY);





    }



}