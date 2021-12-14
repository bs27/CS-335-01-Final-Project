package Tetris;

class rankedScore {
    String player;
    int score;
    rankedScore(){
        player = "No Name";
        score = 0;
    }
    rankedScore(String name , int scoreInsert){
        player = name;
        score = scoreInsert;
    }
    rankedScore(int scoreInsert){
        player = "no Name";
        score = scoreInsert;
    }

    public int getScore() {
        return score;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setScore(int score) {
        this.score = score;
    }
}