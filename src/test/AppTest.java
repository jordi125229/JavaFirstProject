package test;

import cli.Cli;
import cli.Options;

class AppTest {
    public static void main(String[] args) {
        Cli cli = new Cli();
        cli.controlLoop();
    }
}
