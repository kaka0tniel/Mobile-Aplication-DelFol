package com.otniel.delfol.delfol.Model;

/**
 * Created by Otniel on 5/13/2018.
 */

public class hasilPertandingan {
        private String liga1;
        private String score;
        private String liga2;
        private String imgURL1;
        private String imgURL2;

        public hasilPertandingan(String liga1, String score, String liga2, String imgURL1, String imgURL2) {
            this.liga1 = liga1;
            this.score = score;
            this.liga2 = liga2;
            this.imgURL1 = imgURL1;
            this.imgURL2 = imgURL2;
        }

        public String getImgURL1() {
            return imgURL1;
        }

        public void setImgURL1(String imgURL1) {
            this.imgURL1 = imgURL1;
        }

        public String getImgURL2() {
            return imgURL2;
        }

        public void setImgURL2(String imgURL2) {
            this.imgURL2 = imgURL2;
        }

        public void setLiga1(String liga1) {
            this.liga1 = liga1;
        }

        public String getLiga1() {
            return liga1;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getLiga2() {
            return liga2;
        }

        public void setLiga2(String liga2) {
            this.liga2 = liga2;
        }

    }
