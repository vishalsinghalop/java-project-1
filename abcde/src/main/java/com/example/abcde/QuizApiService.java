package com.example.abcde;

import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class QuizApiService {
    private final OkHttpClient client = new OkHttpClient();
    private static final String BASE_API_URL = "https://opentdb.com/api.php?amount=10&type=multiple"; // Computer science questions

    /**
     * Synchronously fetches quiz questions from the API
     * @return QuizData object containing questions and answers
     * @throws IOException if network request fails
     */
    public QuizData fetchQuizQuestions(int n) throws IOException {
        String API_URL = BASE_API_URL + "&category=" +n;

        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String responseBody = response.body().string();
            return parseQuizResponse(responseBody);
        }
    }

    /**
     * Parses the JSON response from the API into our QuizData format
     * @param jsonResponse JSON string from the API
     * @return QuizData object with questions and answers
     */
    private QuizData parseQuizResponse(String jsonResponse) {
        JSONObject json = new JSONObject(jsonResponse);
        JSONArray results = json.getJSONArray("results");

        String[][] questions = new String[results.length()][5];
        String[][] answers = new String[results.length()][2];

        for (int i = 0; i < results.length(); i++) {
            JSONObject questionObj = results.getJSONObject(i);

            // Get the question text
            String questionText = questionObj.getString("question")
                    .replace("&quot;", "\"")
                    .replace("&#039;", "'")
                    .replace("&amp;", "&");

            // Store the question in the first position
            questions[i][0] = questionText;

            // Get the correct answer
            String correctAnswer = questionObj.getString("correct_answer")
                    .replace("&quot;", "\"")
                    .replace("&#039;", "'")
                    .replace("&amp;", "&");

            // Store the correct answer in the first position
            questions[i][1] = correctAnswer;

            // Store the correct answer for validation
            answers[i][1] = correctAnswer;

            // Get the incorrect answers
            JSONArray incorrectAnswers = questionObj.getJSONArray("incorrect_answers");
            for (int j = 0; j < incorrectAnswers.length(); j++) {
                String incorrectAnswer = incorrectAnswers.getString(j)
                        .replace("&quot;", "\"")
                        .replace("&#039;", "'")
                        .replace("&amp;", "&");

                // Store incorrect answers in positions 2-4
                questions[i][j + 2] = incorrectAnswer;
            }
        }

        return new QuizData(questions, answers);
    }

    /**
     * Data class to hold quiz questions and answers
     */
    public static class QuizData {
        private final String[][] questions;
        private final String[][] answers;

        public QuizData(String[][] questions, String[][] answers) {
            this.questions = questions;
            this.answers = answers;
        }

        public String[][] getQuestions() {
            return questions;
        }

        public String[][] getAnswers() {
            return answers;
        }
    }
}