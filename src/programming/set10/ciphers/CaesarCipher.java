package programming.set10.ciphers;

public class CaesarCipher {

    private final CryptMode cryptMode;
    private final int key;

    public CaesarCipher(CryptMode cryptMode, int key) {
        this.cryptMode = cryptMode;
        this.key = key;
    }

    /**
     * @return the positive offset between 0 and 25.
     */
    private int getOffset() {
        // fix keys > 26
        int offset = key % 26;
        if (cryptMode == CryptMode.DECRYPT) {
            offset *= -1;
        }
        // an offset of -3 is the same as 23
        if (Math.signum(offset) == -1.0) {
            return 26 + offset;
        }
        return offset;
    }

    /**
     * @param character char to be encoded
     * @return encoded version of char according to this instances encoding settings
     */
    private char encodeChar(char character) {
        int offset = this.getOffset();

        if (!Character.isLetter(character)) {
            return character;
        }

        char encodedChar = (char) (character + offset);

        if ((Character.isLowerCase(character) && (encodedChar > 'z'))
                || (Character.isUpperCase(character) && (encodedChar > 'Z'))) {
            return (char) (character - (26 - offset));
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
