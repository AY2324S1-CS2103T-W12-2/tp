package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_BANK_ACCOUNT = new Prefix("b/");
    public static final Prefix PREFIX_JOIN_DATE = new Prefix("jd/");
    public static final Prefix PREFIX_SALARY = new Prefix("s/");
    public static final Prefix PREFIX_ANNUAL_LEAVE = new Prefix("al/");
}
