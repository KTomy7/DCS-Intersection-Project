package Project;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Intersection {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Intersection";

        pn.NetworkPort = 2000;

        // ------------------------------------------------------------------------

        DataCarQueue p1 = new DataCarQueue();
        p1.Value.Size = 3;
        p1.SetName("p1");
        pn.PlaceList.add(p1);

        DataCar p2 = new DataCar();
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataCar p3 = new DataCar();
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        DataCar p4 = new DataCar();
        p4.SetName("p4");
        pn.PlaceList.add(p4);

        DataCar p5 = new DataCar();
        p5.SetName("p5");
        pn.PlaceList.add(p5);

        DataCar p6 = new DataCar();
        p6.SetName("p6");
        pn.PlaceList.add(p6);

        // T1------------------------------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p2");

        Condition T1Ct1 = new Condition(t1, "p2", TransitionCondition.NotNull);
        Condition T1Ct2 = new Condition(t1, "p1", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        grdT1.Activations.add(new Activation(t1, "p2", TransitionOperation.PopElementWithTargetToQueue, "p1"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2------------------------------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p1");

        Condition T2Ct1 = new Condition(t2, "p1", TransitionCondition.HaveCarForMe);
        Condition T2Ct2 = new Condition(t2, "p3", TransitionCondition.CanAddCars);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        grdT2.Activations.add(new Activation(t2, "p1", TransitionOperation.AddElement, "p3"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3------------------------------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p4");

        Condition T3Ct1 = new Condition(t3, "p1", TransitionCondition.CanAddCars);
        Condition T3Ct2 = new Condition(t3, "p4", TransitionCondition.NotNull);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        grdT3.Activations.add(new Activation(t3, "p4", TransitionOperation.PopElementWithTargetToQueue, "p1"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4------------------------------------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p1");

        Condition T4Ct1 = new Condition(t4, "p1", TransitionCondition.HaveCarForMe);
        Condition T4Ct2 = new Condition(t3, "p5", TransitionCondition.CanAddCars);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;

        grdT4.Activations.add(new Activation(t4, "p1", TransitionOperation.AddElement, "p5"));

        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);

        // T5------------------------------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("p6");

        Condition T5Ct1 = new Condition(t5, "p1", TransitionCondition.CanAddCars);
        Condition T5Ct2 = new Condition(t5, "p6", TransitionCondition.NotNull);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;

        grdT5.Activations.add(new Activation(t5, "p6", TransitionOperation.PopElementWithTargetToQueue, "p1"));

        t5.GuardMappingList.add(grdT5);
        t5.Delay = 0;
        pn.Transitions.add(t5);

        // T6------------------------------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p1");

        Condition T6Ct1 = new Condition(t6, "p7", TransitionCondition.CanAddCars);
        Condition T6Ct2 = new Condition(t6, "p1", TransitionCondition.HaveCarForMe);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;

        grdT6.Activations.add(new Activation(t6, "p1", TransitionOperation.AddElement, "p7"));

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        pn.Transitions.add(t6);

        System.out.println("Intersection started \n ------------------------------");
        pn.Delay = 2000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
