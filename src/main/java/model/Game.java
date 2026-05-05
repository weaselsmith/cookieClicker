package model;

import java.sql.Date;
import java.time.LocalDate;

public class Game {
    private int gameId;
    private long cookies;
    private int numGrandmas;
    private int numFactories;
    private int numWizards;
    private int grandmaLvl;
    private int factoryLvl;
    private int wizardsLvl;
    private LocalDate lastLogin;

    public Game() {
        this.gameId = -1;
        this.cookies = 0;
        this.numGrandmas = 0;
        this.numFactories = 0;
        this.numWizards = 0;
        this.grandmaLvl = 1;
        this.factoryLvl = 1;
        this.wizardsLvl = 1;
        this.lastLogin = LocalDate.now();
    }

    public Game(int gameId, long cookies, int numGrandmas, int numFactories,
                int numWizards, int grandmaLvl, int factoryLvl,
                int wizardsLvl, LocalDate lastLogin) {
        this.gameId = gameId;
        this.cookies = cookies;
        this.numGrandmas = numGrandmas;
        this.numFactories = numFactories;
        this.numWizards = numWizards;
        this.grandmaLvl = grandmaLvl;
        this.factoryLvl = factoryLvl;
        this.wizardsLvl = wizardsLvl;
        this.lastLogin = lastLogin;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public long getCookies() {
        return cookies;
    }

    public void setCookies(long cookies) {
        this.cookies = cookies;
    }

    public int getNumGrandmas() {
        return numGrandmas;
    }

    public void setNumGrandmas(int numGrandmas) {
        this.numGrandmas = numGrandmas;
    }

    public int getNumFactories() {
        return numFactories;
    }

    public void setNumFactories(int numFactories) {
        this.numFactories = numFactories;
    }

    public int getNumWizards() {
        return numWizards;
    }

    public void setNumWizards(int numWizards) {
        this.numWizards = numWizards;
    }

    public int getGrandmaLvl() {
        return grandmaLvl;
    }

    public void setGrandmaLvl(int grandmaLvl) {
        this.grandmaLvl = grandmaLvl;
    }

    public int getFactoryLvl() {
        return factoryLvl;
    }

    public void setFactoryLvl(int factoryLvl) {
        this.factoryLvl = factoryLvl;
    }

    public int getWizardsLvl() {
        return wizardsLvl;
    }

    public void setWizardsLvl(int wizardsLvl) {
        this.wizardsLvl = wizardsLvl;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }
}