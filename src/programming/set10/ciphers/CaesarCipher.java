package programming.set10.ciphers;

public class CaesarCipher {

    private final CryptMode cryptMode;
    private final int key;

    public CaesarCipher(CryptMode cryptMode, int key) {
        this.cryptMode = cryptMode;
        this.key = key;


    }

    private int getOffset() {
        if (cryptMode == CryptMode.DECRYPT) {
            return key*-1;
        }
        return key;
    }

    private char encodeChar(char character) {
        int offset = this.getOffset();

        if (!Character.isLetter(character)) {
            return character;
        }

        char encodedChar = (char) (character + offset);

        if ((Character.isLowerCase(character) && (encodedChar > 'z' || encodedChar < 'a'))
                || (Character.isUpperCase(character) && (encodedChar > 'Z' || encodedChar < 'A'))) {
            // Multiply 26 with the signum of offset to walk forward if offset is negative,
            // while we walk backwards if offset is negative
            int correctedOffset = -(26*(int)Math.signum(offset) - offset);
            return (char) (character + correctedOffset);
        }
        return encodedChar;
    }

    /**
     * Encrypts or decrypts the given text, depending on the mode of operation this
     * cypher was created for.
     *
     * @param text
     *            the text to encode.
     * @return encryted or decrypted version of the text.
     */
    public String encode(String text) {

        String result = "";
        for (char character: text.toCharArray()) {
            result += this.encodeChar(character);
        }
        return result;
    }
}
