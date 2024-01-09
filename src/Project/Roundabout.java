package Project;

import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Roundabout {
    public static void main(String[] args) {

        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Roundabout";

        pn.NetworkPort = 2000;

        // ------------------------------------------------------------------------

        DataCarQueue p1 = new DataCarQueue();
        p1.Value.Size = 3;
        p1.SetName("p1");
        pn.PlaceList.add(p1);

        DataCarQueue p2 = new DataCarQueue();
        p2.Value.Size = 3;
        p2.SetName("p2");
        pn.PlaceList.add(p2);

        DataCarQueue p3 = new DataCarQueue();
        p3.Value.Size = 3;
        p3.SetName("p3");
        pn.PlaceList.add(p3);

        DataCarQueue p4 = new DataCarQueue();
        p4.Value.Size = 3;
        p4.SetName("p4");
        pn.PlaceList.add(p4);

        DataCar p5 = new DataCar();
        p5.SetName("p5");
        pn.PlaceList.add(p5);

        DataCar p6 = new DataCar();
        p6.SetName("p6");
        pn.PlaceList.add(p6);

        DataCar p7 = new DataCar();
        p7.SetName("p7");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("p8");
        pn.PlaceList.add(p8);

        DataCar p9 = new DataCar();
        p9.SetName("p9");
        pn.PlaceList.add(p9);

        DataCar p10 = new DataCar();
        p10.SetName("p10");
        pn.PlaceList.add(p10);

        DataTransfer p10_0 = new DataTransfer();
        p10_0.SetName("p10_0");
        p10_0.Value = new TransferOperation("localhost", "1090", "P6");
        pn.PlaceList.add(p10_0);

        // T1------------------------------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "t1";
        t1.InputPlaceName.add("p1");

        Condition T1Ct1 = new Condition(t1, "p1", TransitionCondition.HaveCarForMe);
        Condition T1Ct2 = new Condition(t1, "p2", TransitionCondition.CanAddCars);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;

        grdT1.Activations.add(new Activation(t1, "p1", TransitionOperation.PopElementWithTargetToQueue, "p2"));

        t1.GuardMappingList.add(grdT1);
        t1.Delay = 0;
        pn.Transitions.add(t1);

        // T2------------------------------------------------------------------------
        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "t2";
        t2.InputPlaceName.add("p2");

        Condition T2Ct1 = new Condition(t2, "p2", TransitionCondition.HaveCarForMe);
        Condition T2Ct2 = new Condition(t2, "p1", TransitionCondition.CanAddCars);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;

        grdT2.Activations.add(new Activation(t2, "p2", TransitionOperation.PopElementWithTargetToQueue, "p1"));

        t2.GuardMappingList.add(grdT2);
        t2.Delay = 0;
        pn.Transitions.add(t2);

        // T3------------------------------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "t3";
        t3.InputPlaceName.add("p3");

        Condition T3Ct1 = new Condition(t3, "p3", TransitionCondition.HaveCarForMe);
        Condition T3Ct2 = new Condition(t3, "p2", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;

        grdT3.Activations.add(new Activation(t3, "p3", TransitionOperation.PopElementWithTargetToQueue, "p2"));

        t3.GuardMappingList.add(grdT3);
        t3.Delay = 0;
        pn.Transitions.add(t3);

        // T4------------------------------------------------------------------------
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "t4";
        t4.InputPlaceName.add("p4");

        Condition T4Ct1 = new Condition(t4, "p4", TransitionCondition.HaveCarForMe);
        Condition T4Ct2 = new Condition(t3, "p3", TransitionCondition.CanAddCars);
        T4Ct1.SetNextCondition(LogicConnector.AND, T4Ct2);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;

        grdT4.Activations.add(new Activation(t4, "p4", TransitionOperation.PopElementWithTarget, "p3"));

        t4.GuardMappingList.add(grdT4);
        t4.Delay = 0;
        pn.Transitions.add(t4);

        // T5------------------------------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "t5";
        t5.InputPlaceName.add("p5");

        Condition T5Ct1 = new Condition(t5, "p5", TransitionCondition.NotNull);
        Condition T5Ct2 = new Condition(t5, "p1", TransitionCondition.CanAddCars);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;

        grdT5.Activations.add(new Activation(t5, "p5", TransitionOperation.AddElement, "p1"));

        t5.GuardMappingList.add(grdT5);
        t5.Delay = 0;
        pn.Transitions.add(t5);

        // T6------------------------------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "t6";
        t6.InputPlaceName.add("p2");

        Condition T6Ct1 = new Condition(t6, "p2", TransitionCondition.HaveCarForMe);
        Condition T6Ct2 = new Condition(t6, "p6", TransitionCondition.CanAddCars);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;

        grdT6.Activations.add(new Activation(t6, "p2", TransitionOperation.PopElementWithTarget, "p6"));

        t6.GuardMappingList.add(grdT6);
        t6.Delay = 0;
        pn.Transitions.add(t6);

        // T7------------------------------------------------------------------------
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "t7";
        t7.InputPlaceName.add("p4");

        Condition T7Ct1 = new Condition(t7, "p4", TransitionCondition.HaveCarForMe);
        Condition T7Ct2 = new Condition(t7, "p7", TransitionCondition.CanAddCars);
        T7Ct1.SetNextCondition(LogicConnector.AND, T7Ct2);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;

        grdT7.Activations.add(new Activation(t7, "p4", TransitionOperation.PopElementWithTarget, "p7"));

        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        pn.Transitions.add(t7);

        // T8------------------------------------------------------------------------
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "t8";
        t8.InputPlaceName.add("p8");

        Condition t8Ct1 = new Condition(t8, "p8", TransitionCondition.NotNull);
        Condition t8Ct2 = new Condition(t8, "p4", TransitionCondition.CanAddCars);
        t8Ct1.SetNextCondition(LogicConnector.AND, t8Ct2);

        GuardMapping grdt8 = new GuardMapping();
        grdt8.condition = t8Ct1;

        grdt8.Activations.add(new Activation(t8, "p8", TransitionOperation.AddElement, "p4"));

        t8.GuardMappingList.add(grdt8);
        t8.Delay = 0;
        pn.Transitions.add(t8);

        // T9------------------------------------------------------------------------
        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "t9";
        t9.InputPlaceName.add("p9");

        Condition t9Ct1 = new Condition(t9, "p9", TransitionCondition.NotNull);
        Condition t9Ct2 = new Condition(t9, "p3", TransitionCondition.CanAddCars);
        t9Ct1.SetNextCondition(LogicConnector.AND, t9Ct2);

        GuardMapping grdt9 = new GuardMapping();
        grdt9.condition = t9Ct1;

        grdt9.Activations.add(new Activation(t9, "p9", TransitionOperation.AddElement, "p3"));

        t9.GuardMappingList.add(grdt9);
        t9.Delay = 0;
        pn.Transitions.add(t9);

        // T10------------------------------------------------------------------------
        PetriTransition t10 = new PetriTransition(pn);
        t10.TransitionName = "t10";
        t10.InputPlaceName.add("p3");

        Condition t10Ct1 = new Condition(t10, "p3", TransitionCondition.HaveCarForMe);
        Condition t10Ct2 = new Condition(t10, "p10", TransitionCondition.CanAddCars);
        t10Ct1.SetNextCondition(LogicConnector.AND, t10Ct2);

        GuardMapping grdt10 = new GuardMapping();
        grdt10.condition = t10Ct1;

        grdt10.Activations.add(new Activation(t9, "p3", TransitionOperation.PopElementWithTarget, "p10"));

        t10.GuardMappingList.add(grdt10);
        t10.Delay = 0;
        pn.Transitions.add(t10);

        // T10_Out------------------------------------------------------------------------
        PetriTransition t10_out = new PetriTransition(pn);
        t10_out.TransitionName = "t10_out";
        t10_out.InputPlaceName.add("p10");

        Condition t10_outCt1 = new Condition(t10_out, "p10", TransitionCondition.NotNull);

        GuardMapping grdt10_out = new GuardMapping();
        grdt10_out.condition = t10_outCt1;

        grdt10_out.Activations.add(new Activation(t10_out, "p10", TransitionOperation.SendOverNetwork, "p10_out"));

        t10_out.GuardMappingList.add(grdt10_out);
        t10_out.Delay = 0;
        pn.Transitions.add(t10_out);

        System.out.println("RoundAbout started \n ------------------------------");
        pn.Delay = 2000;

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
