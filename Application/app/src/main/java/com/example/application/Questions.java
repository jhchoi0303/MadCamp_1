package com.example.application;


public class Questions {

    public String mQuestions[] = {
            "What language does Android Studio use?",
            "What shows a list of items?",
            "What is not one of the templates in Android Studio?",
            "What command do we use to push?",
            "What command do we use to switch branches?",
            "What is the symbol color of Korea University?",
            "Which one has highest social rank in Kaist?",
            "What is 1+1?",
            "What is Github's mascot?"
    };

    private String mChoices[][] = {
            {"java", "c","python","c#"},
            {"ListView", "Grid","TextView","ImageView"},
            {"Tabbed Activity", "Basic Activity","Empty Activity","List Activity"},
            {"commit", "git log","git push","git branch"},
            {"git branch", "git checkout","git push","git commit"},
            {"Crimson","Pink","Red","Blue"},
            {"Undergraduate","Cat","Graduate","Goose"},
            {"2","0","1","3"},
            {"Mickey mouse","Octocat","Tiger","Lion"}
    };

    private String mCorrectAnswers[] = {"java", "ListView", "List Activity", "git push","git checkout","Crimson","Goose","2","Octocat"};

    public String getQuestion(int a) {
        String question = mQuestions[a];
        return  question;
    };

    public String getChoice1(int a) {
        String choice = mChoices[a][0];
        return  choice;
    };

    public String getChoice2(int a) {
        String choice = mChoices[a][1];
        return  choice;
    };

    public String getChoice3(int a) {
        String choice = mChoices[a][2];
        return  choice;
    };

    public String getChoice4(int a) {
        String choice = mChoices[a][3];
        return  choice;
    };

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}