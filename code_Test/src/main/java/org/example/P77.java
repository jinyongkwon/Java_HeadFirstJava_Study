package org.example;

class P77 {

    static class DrumKit {
        boolean topHat = true;
        boolean snare = true;

        void playSnare() {
            System.out.print("bang bang ba-bang");
        }

        void playTopHat() {
            System.out.print("ding ding da-ding");
        }
    }

    public static void main(String[] args) {
        DrumKit d = new DrumKit();
        d.playSnare();
        d.playTopHat();
        d.snare = false;
        if (d.snare == true) {
            d.playSnare();
        }
    }
}