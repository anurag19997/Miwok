package com.example.android.miwokudacity;

/**
 * Created by Kamal Dev Sharma on 6/11/2017.
 */

public class Words {
    String englishWord;
    String miwokWord;
    int imageId = 0;
    int audioId = 0;

    public Words(String englishWord, String miwokWord, int imageId, int audioId) {
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.imageId = imageId;
        this.audioId = audioId;
    }

    public Words(String englishWord, String miwokWord, int audioId) {
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.audioId = audioId;
    }


    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public void setMiwokWord(String miwokWord) {
        this.miwokWord = miwokWord;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }
}
