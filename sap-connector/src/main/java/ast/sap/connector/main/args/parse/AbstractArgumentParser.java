package ast.sap.connector.main.args.parse;

import java.util.regex.Pattern;

public abstract class AbstractArgumentParser implements ArgumentParser {
    private final Pattern pattern;
    private final String prefix;

    AbstractArgumentParser(String prefix, String search) {
        pattern = Pattern.compile(prefix + search);
        this.prefix = prefix;
    }

    @Override
    public boolean matches(String arg) {
        return pattern.matcher(arg).matches();
    }

    @Override
    public String getArgValue(String arg) {
        return arg.split(prefix)[1];
    }

}
