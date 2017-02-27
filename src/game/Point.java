package game;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Point {

    public enum Notation {
        NUMERICAL,
        ALPHABETIC
    }

    private static Notation NOTATION = Notation.NUMERICAL;

    public static void setNotation(Notation notation) {
        NOTATION = notation;
    }

    public int x;
    public int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Point)) return false;
        Point oPoint = (Point)other;
        return x == oPoint.x && y == oPoint.y;
    }

    Point(String str) {
        if (NOTATION == Notation.NUMERICAL) {
            String regexp = "\\((\\d+),(\\d+)\\)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                this.x = Integer.valueOf(matcher.group(1));
                this.y = Integer.valueOf(matcher.group(2));
            }
            else {
                throw new IllegalArgumentException("Wrong point format: " + str);
            }
        }
        else {
            String regexp = "([A-Z]+)(\\d+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String s = matcher.group(1);
                int n = -1, m = 1;
                for (int i = s.length()-1; i >= 0; --i) {
                    n = n + m * (s.charAt(i) - 'A' + 1);
                    m *= 26;
                }
                this.x = n;
                this.y = Integer.valueOf(matcher.group(2)) - 1;
            }
            else {
                throw new IllegalArgumentException("Wrong point format: " + str);
            }
        }
    }

    @Override
    public String toString() {
        if (NOTATION == Notation.NUMERICAL || x < 0 || y < 0) {
            return MessageFormat.format("({0},{1})", x, y);
        }
        else {
            String res = "";
            int n = this.x + 1;
            while (n > 0) {
                res = Character.toString((char)((n - 1) % 26 + 'A')) + res;
                n = (n - 1) / 26;
            }
            return res + String.valueOf(this.y + 1); // Y=0 --> 1
        }
    }
}
