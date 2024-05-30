package org.it.uniba.minima.Control;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.it.uniba.minima.Boundary.outputDisplayManager;
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
        if (i.hasName("Pala")) {
            game.addInventory((Item) GameManager.getAgentFromName("Chiave"));
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
        if (i1.hasName("TorciaAccesa") && i2.hasName("TorciaSpenta")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("TorciaAccesa2"));
            return true;
        }
        if (i1.hasName("Coltello") && i2.hasName("FrecciaSpuntata")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("FrecciaAppuntita"));
            return true;
        }
        if (i1.hasName("Coltello") && i2.hasName("FrecciaPiumata")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("FrecciaCompleta"));
            return true;
        }
        if (i1.hasName("Pietra") && i2.hasName("Grano")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Farina"));
            return true;
        }
        if (i1.hasName("Pietra") && i2.hasName("CannaDaZucchero")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Zucchero"));
            return true;
        }
        if (i1.hasName("Acqua") && i2.hasName("Lente")) {
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("LentePulita"));
            return true;
        }
        return false;
    }

    public boolean executeUseCombinationInRoom(Item i1, Item i2) {
        if (i1.hasName("Chiave") && i2.hasName("Entrata")) {
            game.unlockCorridor("Esterno", "ingressoPiramide");
            return true;
        }
        if (i1.hasName("Piffero") && i2.hasName("Serpenti")) {
            game.setRoomState("SalaSerpenti", "SerpentiAmmansiti");
            Item torcia1 = (Item) GameManager.getAgentFromName("TorciaSpenta");
            torcia1.setPickable(true);
            return true;
        }
        if (i1.hasName("Corda") && i2.hasName("Gancio")) {
            game.setRoomState("SalaCascata", "CascataSpenta");
            Item torcia2 = (Item) GameManager.getAgentFromName("TorciaAccesa");
            torcia2.setPickable(true);
            return true;
        }
        if (i1.hasName("TorciaSpenta") && i2.hasName("Brace")) {
            game.removeInventory(i1);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("TorciaAccesa2"));
            return true;
        }
        if ((i1.hasName("TorciaAccesa") || i1.hasName("TorciaAccesa2")) && (i2.hasName("Inserto1") || i2.hasName("Inserto2"))) {
            game.removeInventory(i1);
            game.getCurrentRoom().removeAgent(i2);

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
            return true;
        }
        if (i1.hasName("ArcoEFreccia") && i2.hasName("OcchioDiVetro")) {
            game.removeInventory(i1);
            game.addInventory((Item) GameManager.getAgentFromName("Arco"));
            game.getCurrentRoom().removeAgent(i2);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("OcchioDiVetroRotto"));
            game.unlockCorridor("Corridoio2", "Corridoio3");
            game.setRoomState("Corridoio2", "Aperto");
            return true;
        }
        if (i1.hasName("Impasto2") && i2.hasName("Fuoco")) {
            game.removeInventory(i1);
            game.getCurrentRoom().removeAgent(i2);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("ImpastoCotto"));
            return true;
        }
        if (i1.hasName("Torta") && i2.hasName("Piedistallo")) {
            game.removeInventory(i1);
            game.getCurrentRoom().removeAgent(i2);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("PiedistalloConTorta"));
            game.addInventory((Item) GameManager.getAgentFromName("Ankh"));
            game.setRoomState("StanzaTorta", "TortaSulPiedistallo");
            return true;
        }
        if (i1.hasName("Olio") && i2.hasName("Buco")) {
            game.removeInventory(i1);
            game.getCurrentRoom().removeAgent(i2);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("BucoPieno"));
            game.unlockCorridor("Corridoio3", "Corridoio4");
            return true;
        }
        if (i1.hasName("LentePulita") && i2.hasName("BucoPieno")) {
            game.removeInventory(i1);
            game.getCurrentRoom().removeAgent(i2);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("BucoConLente"));
            return true;
        }
        if (i1.hasName("Acqua") && i2.hasName("BucoConLente")) {
            game.removeInventory(i1);
            game.getCurrentRoom().removeAgent(i2);
            game.getCurrentRoom().addAgent(GameManager.getAgentFromName("BucoPotente"));
            game.addInventory((Item) GameManager.getAgentFromName("Hekat"));
            game.setRoomState("StanzaAltare", "FuocoAcceso");
            return true;
        }
        return false;
    }

    public boolean executeFuseCombination(Item i1, Item i2) {
        if (i1.hasName("FrecciaSpuntata") && i2.hasName("Piuma")
        || i1.hasName("Piuma") && i2.hasName("FrecciaSpuntata")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("FrecciaPiumata"));
            return true;
        }
        if (i1.hasName("FrecciaAppuntita") && i2.hasName("Piuma")
        || i1.hasName("Piuma") && i2.hasName("FrecciaAppuntita")){
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("FrecciaCompleta"));
            return true;
        }
        if (i1.hasName("ArcoRotto") && i2.hasName("Corda")
        || i1.hasName("Corda") && i2.hasName("ArcoRotto")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Arco"));
            return true;
        }
        if (i1.hasName("Arco") && i2.hasName("FrecciaCompleta")
        || i1.hasName("FrecciaCompleta") && i2.hasName("Arco")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("ArcoEFreccia"));
            return true;
        }
        if (i1.hasName("Farina") && i2.hasName("Zucchero")
        || i1.hasName("Zucchero") && i2.hasName("Farina")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Impasto1"));
            return true;
        }
        if (i1.hasName("Zucchero") && i2.hasName("Uova")
        || i1.hasName("Uova") && i2.hasName("Zucchero")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Impasto1"));
            return true;
        }
        if (i1.hasName("Farina") && i2.hasName("Uova")
        || i1.hasName("Uova") && i2.hasName("Farina")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Impasto1"));
            return true;
        }
        if (i1.hasName("Impasto1") && i2.hasName("Uova")
        || i1.hasName("Uova") && i2.hasName("Impasto1")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Impasto2"));
            return true;
        }
        if (i1.hasName("Impasto1") && i2.hasName("Zucchero")
        || i1.hasName("Zucchero") && i2.hasName("Impasto1")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Impasto2"));
            return true;
        }
        if (i1.hasName("Impasto1") && i2.hasName("Farina")
        || i1.hasName("Farina") && i2.hasName("Impasto1")) {
            game.removeInventory(i1);
            game.removeInventory(i2);
            game.addInventory((Item) GameManager.getAgentFromName("Impasto2"));
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
        if (i.hasName("Hekat") || i.hasName("Nekekth") || i.hasName("Ankh") && c.hasName("Osiride")) {
            game.removeInventory(i);
            game.getCurrentRoom().addAgent(i);
            i.setPickable(false);
            if (i.hasName("Hekat")) {
                game.setRoomState("StanzaSarcofago", "HekatDato");
            } else if (i.hasName("Nekekth")) {
                game.setRoomState("StanzaSarcofago", "NekekthDato");
            } else {
                game.setRoomState("StanzaSarcofago", "AnkhDato");
            }
            if (game.getCurrentRoom().hasAgent(GameManager.getAgentFromName("Hekat")) &&
                game.getCurrentRoom().hasAgent(GameManager.getAgentFromName("Nekekth")) &&
                game.getCurrentRoom().hasAgent(GameManager.getAgentFromName("Ankh"))) {
                //start final event
            }
            return true;
        }
        return false;
    }

    public boolean launchSpecialEvent(CommandType c, Personage p) {
        if (c == CommandType.TALK && p.hasName("Sfinge") && game.getCurrentRoom().getState().equals("Start")) {
            userInputFlow.Event = 1;
            GameGUI.setImagePanel("Wordle");
            outputDisplayManager.displayText("Hai iniziato il Wordle!");
        }
        return false;
    }
}
