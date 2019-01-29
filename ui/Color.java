package ui;

public interface Color {
    String RED    = "\u001b[01;31m";  // for enemy
    String GREEN  = "\u001b[01;32m";  // acted ally & detected cell
    String YELLOW = "\u001b[01;33m";  // exploded cell
    String BLUE   = "\u001b[01;34m";  // standby ally
    String PINK   = "\u001b[01;35m";
    String SKY    = "\u001b[01;36m";  // selected ally & available cell
    String WHITE  = "\u001b[01;37m";  // normal cell
    String END    = "\u001b[00m";
}
