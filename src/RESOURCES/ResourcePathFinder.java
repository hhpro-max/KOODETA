package RESOURCES;

public class ResourcePathFinder {
    public String getName(ImageResource type){
        switch (type){
            case GUIDE:
                return "guide.jpg";
            case SAFIR:
                return "safir.jpg";
            case ADAM_KOSH:
                return "adamkosh.jpg";
            case BAJ_GIR:
                return "bajgir.jpg";
            case SHAH_DOKHT:
                return "shahdokht.jpg";
            case BOZORH_ZADE:
                return "bozorgzade.jpg";
            case BACK:
                return "back.jpg";
            default:
                return type.toString();
        }
    }
}
