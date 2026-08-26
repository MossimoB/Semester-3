package mossimo.bianco.lab01;

/**
 * @author sport
 */
public class ZipCode {

    public int Zip;
    private boolean valid;

    private static final String[] DIGIT_TO_CODE = {
        "11000",
        "00011",
        "00101",
        "00110",
        "01001",
        "01010",
        "01100",
        "10001",
        "10010",
        "10100"
    };

    public ZipCode(int zipcode) {
        if (zipcode < 0 || zipcode > 99999) {
            System.out.println("Error: zip code " + zipcode
                    + " is out of range (must be 0-99999).");
            this.valid = false;
            this.Zip = 0;
            return;
        }
        this.Zip = zipcode;
        this.valid = true;
    }

    public ZipCode(String barCode) {
        this.valid = true;
        this.Zip = 0;

        if (barCode.length() != 27) {
            System.out.println("Error: bad length. Expected 27 characters, got "
                    + barCode.length() + ".");
            this.valid = false;
            return;
        }

        if (barCode.charAt(0) != '1' || barCode.charAt(barCode.length() - 1) != '1') {
            System.out.println("Error: bad start/end character. "
                    + "Barcode must begin and end with '1'.");
            this.valid = false;
            return;
        }

        for (int i = 0; i < barCode.length(); i++) {
            char c = barCode.charAt(i);
            if (c != '0' && c != '1') {
                System.out.println("Error: bad digit. Found '" + c
                        + "' which is not 0 or 1.");
                this.valid = false;
                return;
            }
        }

        this.Zip = parseBarCode(barCode);
    }

    public String GetBarCode() {
        if (!valid) {
            return "Error: cannot build barcode, zip code is invalid.";
        }

        String digits = String.format("%05d", this.Zip);

        StringBuilder result = new StringBuilder();
        result.append('1');

        for (int i = 0; i < digits.length(); i++) {
            int d = digits.charAt(i) - '0';
            result.append(DIGIT_TO_CODE[d]);
        }

        result.append('1');
        return result.toString();
    }

    private int parseBarCode(String barCode) {
        String middle = barCode.substring(1, barCode.length() - 1);

        StringBuilder zipDigits = new StringBuilder();

        for (int groupStart = 0; groupStart < middle.length(); groupStart += 5) {
            String group = middle.substring(groupStart, groupStart + 5);

            int[] weights = {7, 4, 2, 1, 0};
            int sum = 0;
            int onesCount = 0;

            for (int i = 0; i < 5; i++) {
                if (group.charAt(i) == '1') {
                    sum += weights[i];
                    onesCount++;
                }
            }

            if (onesCount != 2) {
                System.out.println("Error: bad sequence. Group \"" + group
                        + "\" does not contain exactly two 1's.");
                this.valid = false;
                return -1;
            }

            int digit = (sum == 11) ? 0 : sum;
            zipDigits.append(digit);
        }

        return Integer.parseInt(zipDigits.toString());
    }
}