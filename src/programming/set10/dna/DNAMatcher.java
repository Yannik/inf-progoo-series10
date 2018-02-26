package programming.set10.dna;

public class DNAMatcher {
    private DNAMatcher() {

    }

    /**
     * @param base dna
     * @return complement dna
     */
    public static char getLinkable(char base) {
        switch (base) {
            case 'A': return 'T';
            case 'T': return 'A';
            case 'C': return 'G';
            case 'G': return 'C';
        }
        throw new IllegalArgumentException("Invalid DNA");
    }

    /**
     * Returns the index of the first position in baseDNA where candidateDNA can
     * bind to baseDNA, if any.
     *
     * @param baseDNA
     *            the base DNA string.
     * @param candidateDNA
     *            the DNA string to try to bind to the base DNA.
     * @return index of the first binding position or {@code -1} if candidateDNA
     *         cannot bind to baseDNA. Also returns {@code -1} if either of the DNA
     *         strings contains characters other than A, C, G, and T.
     */
    public static int findFirstBindingPosition(String baseDNA, String candidateDNA) {
        String matchingDNA;
        try {
            matchingDNA = candidateDNA
                    .chars()
                    .map(s -> getLinkable((char) s))
                    .parallel()
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            // checks if baseDNA contains any invalid chars
            baseDNA
                    .chars()
                    .parallel()
                    .forEach(s -> getLinkable((char) s));

        } catch (IllegalArgumentException e) {
            return -1;
        }


        int max = baseDNA.length() - matchingDNA.length();
        char first = matchingDNA.charAt(0);

        for (int i = 0; i <= max; i++) {
            /* Look for first character. */
            if (baseDNA.charAt(i) != first) {
                while (++i <= max && baseDNA.charAt(i) != first);
            }

            /* Found first character, now look at the rest of v2 */
            if (i <= max) {
                int j = i + 1;
                int end = j + matchingDNA.length() - 1;
                for (int k = 1; j < end && baseDNA.charAt(j)
                        == matchingDNA.charAt(k); j++, k++);

                if (j == end) {
                    /* Found whole string. */
                    return i;
                }
            }
        }

        return -1;
    }
}
