package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class LeaderboardActivity extends AppCompatActivity {
    private RecyclerView recyclerViewLeaderboard;
    private TextView textViewUserHighScore;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        recyclerViewLeaderboard = findViewById(R.id.recyclerViewLeaderboard);
        textViewUserHighScore = findViewById(R.id.textViewUserHighScore);
        dbHelper = new DBHelper(this, "accounts_database", null, 2);

        recyclerViewLeaderboard.setLayoutManager(new LinearLayoutManager(this));

        displayLeaderboard();
    }

    private void displayLeaderboard() {
        // Fetch top scores
        Cursor topScoresCursor = dbHelper.getTopScores(10);


        List<LeaderboardEntry> leaderboardEntries = new ArrayList<>();
        int rank = 1;
        while (topScoresCursor.moveToNext()) {
            String username = topScoresCursor.getString(0);
            int highScore = topScoresCursor.getInt(1);
            leaderboardEntries.add(new LeaderboardEntry(rank++, username, highScore));
        }
        topScoresCursor.close();
        //Toast.makeText(this, String.valueOf(leaderboardEntries.size()), Toast.LENGTH_SHORT).show();

        // Set up RecyclerView adapter
        LeaderboardAdapter adapter = new LeaderboardAdapter(leaderboardEntries);
        recyclerViewLeaderboard.setAdapter(adapter);

        // Fetch and display user's high score
        String currentUsername = LogSignActivity.loggedInUser;
        if (currentUsername != null) {
            int userHighScore = dbHelper.getUserHighScore(currentUsername);
            textViewUserHighScore.setText("Your High Score: " + userHighScore);
        } else {
            textViewUserHighScore.setText("Log in to see your high score.");
        }
    }

    // Data class for leaderboard entries
    private static class LeaderboardEntry {
        int rank;
        String username;
        int highScore;

        public LeaderboardEntry(int rank, String username, int highScore) {
            this.rank = rank;
            this.username = username;
            this.highScore = highScore;
        }
    }

    // RecyclerView Adapter
    private static class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

        private List<LeaderboardEntry> entries;

        public LeaderboardAdapter(List<LeaderboardEntry> entries) {
            this.entries = entries;
        }

        @Override
        public ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
            android.view.View view = android.view.LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.leaderboard_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            LeaderboardEntry entry = entries.get(position);
            holder.textViewRank.setText(String.valueOf(entry.rank) + ".");
            holder.textViewUsername.setText(entry.username);
            holder.textViewHighScore.setText(String.valueOf(entry.highScore));
        }

        @Override
        public int getItemCount() {
            return entries.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewRank;
            TextView textViewUsername;
            TextView textViewHighScore;

            public ViewHolder(android.view.View itemView) {
                super(itemView);
                textViewRank = itemView.findViewById(R.id.textViewRank);
                textViewUsername = itemView.findViewById(R.id.textViewUsername);
                textViewHighScore = itemView.findViewById(R.id.textViewHighScore);
            }
        }
    }
}
