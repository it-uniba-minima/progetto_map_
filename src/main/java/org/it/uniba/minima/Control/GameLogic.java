package org.it.uniba.minima.Control;
import org.it.uniba.minima.Boundary.HangmanGame;
import org.it.uniba.minima.Boundary.TriviaGame;
import org.it.uniba.minima.Boundary.outputDisplayManager;
import org.it.uniba.minima.Entity.Agent;
import org.it.uniba.minima.Entity.Personage;
import org.it.uniba.minima.Entity.Game;
import org.it.uniba.minima.Entity.Item;
import org.it.uniba.minima.GUI.GameGUI;
import org.it.uniba.minima.Type.CommandType;

public class GameLogic {
    Game game;

    public GameLogic(Game game) {
        this.game = game;
    }

    public boolean executeUseSingleItem(Item i) {
        if (i.hasName("Pala") && game.getCurrentRoom().getName().equals("Desert")
            && !game.getCurrentRoom().getAgents().contains(GameManager.getAgentFromName("Chiave"))
            && !game.getInventory().contains(GameManager.getAgentFromName("Chiave"))) {

            game.addInventory((Item) GameManager.getAgentFromName("Chiave"));
            return true;
        }
        return false;
    }

    public boolean talkToPersonage(Personage c) {
        if (c.hasName("Sfinge") && game.getCurrentRoom().getState().equals("Start")) {
            userInputFlow.Event = 1;
            GameGUI.setImagePanel("Wordle");
            return true;
        }
        if (c.hasName("Mummia") && game.getCurrentRoom().getState().equals("Start") || game.getCurrentRoom().getState().equals("Sbagliato")) {
            userInputFlow.Event = 2;
            TriviaGame.getQAndA();
            return true;
        }
        if (c.hasName("Osiride") && game.getCurrentRoom().getState().equals("SarcofagoAperto")) {
            game.setRoomState("Stanza10", "OsirideStart");
            game.unlockCorridor("Stanza10", "Stanza7");
            game.unlockCorridor("Stanza10", "Stanza8");
            game.unlockCorridor("Stanza10", "Stanza9");
            return true;
        }
        if (c.hasName("Osiride") && game.getCurrentRoom().getState().equals("OsirideEnd")) {
            userInputFlow.Event = 6;
            return true;
        }
        return false;
    }

    public boolean executeUseCombinationInInventory(Item i1, Item i2) {
        if (i1.hasName("Coltello") && i2.hasName("Bastone")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Piffero"));
            return true;
        }
        if (i1.hasName("Coltello") && i2.hasName("FrecciaSmussata")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("FrecciaAffilata"));
            return true;
        }
        if (i1.hasName("Coltello") && i2.hasName("FrecciaPiuma")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Freccia"));
            return true;
        }
        if (i1.hasName("Pietra") && i2.hasName("Grano")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Farina"));
            return true;
        }
        if (i1.hasName("Pietra") && i2.hasName("CannaZucchero")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Zucchero"));
            return true;
        }
        return false;
    }

    public boolean executeTake(Item i) {
        if (i.hasName("TorciaAccesa2") && game.getCurrentRoom().getState().equals("AcquaOn")) {
            game.removeInventory(i);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("TorciaSpenta2"));
            // TODO: lancia messagio da db da fare
            return true;
        }
        if ((i.hasName("TorciaAccesa1") || i.hasName("TorciaAccesa2")) && !game.getRoomState("Stanza1").equals("Torcia1")) {
            game.setRoomState("Stanza1", "Luce");
            return true;
        }
        return false;
    }

    public boolean executeUseCombinationInRoom(Item i1, Item i2) {
        if (i1.hasName("Chiave") && i2.hasName("Porta")) {
            game.unlockCorridor("Desert", "Stanza1");
            return true;
        }
        if (i1.hasName("Piffero") && i2.hasName("Serpenti")) {
            game.setRoomState("Stanza2", "SerpentiOff");
            Item torcia1 = (Item) GameManager.getAgentFromName("TorciaAccesa1");
            torcia1.setPickable(true);
            return true;
        }
        if (i1.hasName("Corda") && i2.hasName("Leva")) {
            game.setRoomState("Stanza3", "AcquaOff");
            Item torcia2 = (Item) GameManager.getAgentFromName("TorciaAccesa2");
            torcia2.setPickable(true);
            return true;
        }
        if (i1.hasName("TorciaSpenta2") && i2.hasName("Brace")) {
            game.removeInventory(i1);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("TorciaAccesa2"));
            return true;
        }
        if ((i1.hasName("TorciaAccesa1") || i1.hasName("TorciaAccesa2")) && (i2.hasName("Statua"))) {
            game.removeInventory(i1);
            if (game.getCurrentRoom().getState().equals("Luce")) {
                game.setRoomState("Stanza1", "Torcia1");
            } else {
                game.setRoomState("Stanza1", "Torcia2");
                game.unlockCorridor("Stanza1", "Stanza4");
            }
            /*
            Item insertoX = (Item) GameManager.getAgentFromName("InsertoAccesoX");
            Item inserto = (Item) GameManager.getAgentFromName("InsertoAcceso");

            if (game.getCurrentRoom().hasAgent(insertoX)) {
                game.getCurrentRoom().addAgent(inserto);
                game.unlockCorridor("ingressoPiramide", "Corridoio1");
                game.setRoomState("ingressoPiramide", "CorridoioAperto");
            } else {
                game.getCurrentRoom().addAgent(insertoX);
                game.setRoomState("ingressoPiramide", "Inserto1Acceso");
            }
             */
            return true;
        }
        if (i1.hasName("Piuma") && i2.hasName("Pergamena") && game.getCurrentRoom().getState().equals("Start") || game.getCurrentRoom().getState().equals("Sbagliato")) {
            userInputFlow.Event = 4;
            GameGUI.setImagePanel("Impiccato");
            return true;
        }

        if (i1.hasName("ArcoFreccia") && i2.hasName("Target")) {
            game.removeInventory(i1);
            game.addInventory((Item) GameManager.getAgentFromName("Arco"));
            game.setRoomState("Stanza5", "End");
            game.unlockCorridor("Stanza5", "Stanza6");
            return true;
        }

        if (i1.hasName("Pala") && i2.hasName("Sarcofago")) {
            game.setRoomState("Stanza10", "SarcofagoAperto");
            return true;
        }

        if (i1.hasName("ImpastoCompleto") && i2.hasName("Fuoco")) {
            game.removeInventory(i1);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoCotto"));
            return true;
        }

        if (i1.hasName("Torta") && i2.hasName("Piedistallo")) {
            game.removeInventory(i1);
            game.setRoomState("Stanza8", "End");
            game.addInventory((Item) GameManager.getAgentFromName("Ankh"));
            return true;
        }

        if (i1.hasName("Olio") && i2.hasName("Buca")) {
            game.removeInventory(i1);
            game.setRoomState("Stanza9", "Olio");
            return true;
        }

        if (i1.hasName("Vetro") && i2.hasName("Buca")) {
            if (game.getCurrentRoom().getState().equals("Olio")) {
                game.removeInventory(i1);
                game.setRoomState("Stanza9", "Vetro");
            } else {
                // fai stampa
            }
            return true;
        }

        if (i1.hasName("Acqua") && i2.hasName("Buca")) {
            if (game.getCurrentRoom().getState().equals("Vetro")) {
                game.removeInventory(i1);
                game.setRoomState("Stanza9", "Acqua");
                game.getCurrentRoom().addAgent(GameManager.getAgentFromName("Hekat"));
            } else {
                // fai stampa
            }
            return true;
        }

        return false;
    }

    public boolean executeFuseCombination(Item i1, Item i2) {
        if (i1.hasName("FrecciaSmussata") && i2.hasName("Piuma")
        || i1.hasName("Piuma") && i2.hasName("FrecciaSmussata")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("FrecciaPiuma"));
            return true;
        }

        if (i1.hasName("FrecciaAffilata") && i2.hasName("Piuma")
        || i1.hasName("Piuma") && i2.hasName("FrecciaAffilata")){
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Freccia"));
            return true;
        }

        if (i1.hasName("ArcoRotto") && i2.hasName("Corda")
        || i1.hasName("Corda") && i2.hasName("ArcoRotto")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Arco"));
            return true;
        }

        if (i1.hasName("Arco") && i2.hasName("Freccia")
        || i1.hasName("Freccia") && i2.hasName("Arco")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ArcoFreccia"));
            return true;
        }

        if (i1.hasName("Farina") && i2.hasName("Zucchero")
        || i1.hasName("Zucchero") && i2.hasName("Farina")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoSecco"));
            return true;
        }

        if (i1.hasName("Zucchero") && i2.hasName("Uova")
        || i1.hasName("Uova") && i2.hasName("Zucchero")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoDolce"));
            return true;
        }

        if (i1.hasName("Farina") && i2.hasName("Uova")
        || i1.hasName("Uova") && i2.hasName("Farina")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoUmido"));
            return true;
        }
        if (i1.hasName("ImpastoSecco") && i2.hasName("Uova")
        || i1.hasName("Uova") && i2.hasName("ImpastoSecco")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoCompleto"));
            return true;
        }

        if (i1.hasName("ImpastoUmido") && i2.hasName("Zucchero")
        || i1.hasName("Zucchero") && i2.hasName("ImpastoUmido")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoCompleto"));
            return true;
        }
        if (i1.hasName("ImpastoDolce") && i2.hasName("Farina")
        || i1.hasName("Farina") && i2.hasName("ImpastoDolce")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ImpastoCompleto"));
            return true;
        }
        if (i1.hasName("ImpastoCotto") && i2.hasName("LatteVecchio")
        || i1.hasName("LatteVecchio") && i2.hasName("ImpastoCotto")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Torta"));
            return true;
        }
        return false;
    }

    public boolean executeGiveCombination(Item i, Personage c) {
        if (i.hasName("Hekat") || i.hasName("Nekhekh") || i.hasName("Ankh") && c.hasName("Osiride")) {
            game.removeInventory(i);
            game.getCurrentRoom().addAgent(i);
            i.setPickable(false);

            if (i.hasName("Hekat")) {
                game.setRoomState("Stanza10", "OsirideHekat");
            } else if (i.hasName("Nekhekh")) {
                game.setRoomState("Stanza10", "OsirideNekhekh");
            } else {
                game.setRoomState("Stanza10", "OsirideAnkh");
            }

            if (game.getCurrentRoom().hasAgent(GameManager.getAgentFromName("Hekat")) &&
                game.getCurrentRoom().hasAgent(GameManager.getAgentFromName("Nekhekh")) &&
                game.getCurrentRoom().hasAgent(GameManager.getAgentFromName("Ankh"))) {

                game.setRoomState("Stanza10", "OsirideEnd");}
            return true;
        }
        return false;
    }

    public boolean launchSpecialEvent(CommandType c, Agent a) {
        if (c == CommandType.OSSERVA && a.hasName("Mattonella")
        && (game.getCurrentRoom().getState().equals("Start")
            || game.getCurrentRoom().getState().equals("Sbagliato"))) {
            
            userInputFlow.Event = 3;
            GameGUI.setImagePanel("Mattonelle");
            return true;
        }
        return false;
    }
}
