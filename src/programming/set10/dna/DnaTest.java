package programming.set10.dna;

public class DnaTest {

    public static void main(String[] args) {
        //DNAMatcher.findFirstBindingPosition("TCTTCGCCATGTATGAAAGTAACATTGGTCT", "TCGTTAATTGGCGCAAGAAGCGGTACATACTTTCATTGTAACCAGAGCGTAATCAAAGA");
        System.out.println(DNAMatcher.findFirstBindingPosition("CGATTTACCATCTCFAIL", "AATGGT"));
    }
}
