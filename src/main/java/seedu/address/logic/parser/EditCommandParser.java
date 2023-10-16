package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                    PREFIX_BANK_ACCOUNT, PREFIX_JOIN_DATE, PREFIX_SALARY, PREFIX_ANNUAL_LEAVE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
            PREFIX_BANK_ACCOUNT, PREFIX_JOIN_DATE, PREFIX_SALARY, PREFIX_ANNUAL_LEAVE);

        EditEmployeeDescriptor EditEmployeeDescriptor = new EditEmployeeDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            EditEmployeeDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            EditEmployeeDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            EditEmployeeDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            EditEmployeeDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_BANK_ACCOUNT).isPresent()) {
            EditEmployeeDescriptor.setBankAccount(
                ParserUtil.parseBankAccount(argMultimap.getValue(PREFIX_BANK_ACCOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_JOIN_DATE).isPresent()) {
            EditEmployeeDescriptor.setJoinDate(ParserUtil.parseJoinDate(argMultimap.getValue(PREFIX_JOIN_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            EditEmployeeDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_ANNUAL_LEAVE).isPresent()) {
            EditEmployeeDescriptor.setAnnualLeave(
                ParserUtil.parseAnnualLeave(argMultimap.getValue(PREFIX_ANNUAL_LEAVE).get()));
        }

        if (!EditEmployeeDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, EditEmployeeDescriptor);
    }
}
