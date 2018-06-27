package com.vinnytsoi;


import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Tree.TreeContextClickEvent;
import com.vaadin.ui.renderers.ButtonRenderer;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        // layout.setWidth(350, Unit.PIXELS);

        Label label1 = new Label("Label Name: ",ContentMode.TEXT);

        TextField txtfield1 = new TextField("TextField");

        txtfield1.setStyleName("dashing");
        txtfield1.setValue("Initial Content");
        txtfield1.setMaxLength(20);


        Label counterLabel = new Label();
        counterLabel.setValue(txtfield1.getValue().length() + " of " + txtfield1.getMaxLength());

        txtfield1.addValueChangeListener(event ->
            {
                int lengthCounter = event.getValue().length();
                counterLabel.setValue(lengthCounter + " of " + txtfield1.getMaxLength());
            }
        );

        // Label label1 = new Label("<b>ContentMode.TEXT</b> - \nLorem ipsum dolor sit amet, consectetur adipiscing elit. Duis erat urna, maximus a tincidunt ac, \n" +
        // "fermentum nec ipsum. Etiam risus risus, <b>rhoncus semper diam at, dignissim iaculis massa.</b>",ContentMode.TEXT);

        // Label label2 = new Label("<b>ContentMode.PREFORMATTED</b> - \nLorem ipsum dolor sit amet, consectetur adipiscing elit. Duis erat urna, maximus a tincidunt ac, \n" +
        // "fermentum nec ipsum. Etiam risus risus, <b>rhoncus semper diam at, dignissim iaculis massa.</b>",ContentMode.PREFORMATTED);
        
        // Label label3 = new Label("<b>ContentMode.HTML</b> - \nLorem ipsum dolor sit amet, consectetur adipiscing elit. Duis erat urna, maximus a tincidunt ac, \n" +
        // "fermentum nec ipsum. Etiam risus risus, <br> <b>rhoncus semper diam at, dignissim iaculis massa.</b>",ContentMode.HTML);

        Button demoButton = new Button ("Demo Button");

        demoButton.setStyleName("custom");

        Label lblCustom = new Label();
        demoButton.addClickListener(event -> 
            {
                //Label lblCustom = new Label(); //Leave this line in to accumulate labels
                lblCustom.setValue(txtfield1.getValue());
                layout.addComponent(lblCustom);
                Notification.show("New label \"" + txtfield1.getValue() + "\" has been added with button click");
            }
        );
        
        CheckBox chbCustomButtons = new CheckBox("Custom Buttons: ",true);
        CheckBox chbNormalButtons = new CheckBox("Normal Buttons: ");
        
        chbCustomButtons.addValueChangeListener(event -> 
            {
                Button customButton = new Button("New Custom Button");
                customButton.setStyleName("custom");
                layout.addComponent(customButton);
            }
        );

        chbNormalButtons.addValueChangeListener(event -> 
            {
                TextField normalButtonTextField = new TextField("Normal Button Textfield");
                normalButtonTextField.setStyleName("dashing");
                normalButtonTextField.setValue("Initial Value");
                layout.addComponent(normalButtonTextField);
            }
        );

        RadioButtonGroup<String> singleGroup = new RadioButtonGroup<>("Radio Button Group (Single)");
        singleGroup.setItems("Jean","Shirt","Shoes");

        CheckBoxGroup<String> multiGroup = new CheckBoxGroup<>("Check Box Group (Multi)");
        multiGroup.setItems("PC", "PC + Monitor", "PC + Monitor + Keyboard");

        RadioButtonGroup<String> excludedGroup = new RadioButtonGroup<>("Excluded Group");
        excludedGroup.setItems("Jean","Shirt","Shoes");
        excludedGroup.setItemEnabledProvider(event -> !"Jean".equals(event));

        ComboBox<String> combo = new ComboBox<>("Recipes");
        ArrayList<String> recipesArrayList = new ArrayList<>();

        recipesArrayList.add("Mac & Cheese");
        recipesArrayList.add("Pizza");
        recipesArrayList.add("Fried Rice");
        recipesArrayList.add("Noodle Fiesta");
        recipesArrayList.add("Creamy Chorizo Fish");

        combo.setItems(recipesArrayList);

        // To add items to combo box
        combo.setNewItemProvider(inputString ->
                {
                    String newRecipe = inputString;
                    recipesArrayList.add(newRecipe);

                    combo.setItems(recipesArrayList);

                    return Optional.of(newRecipe);
                }
        );

        ArrayList<Student> studentArrayList = new ArrayList<>();
        Grid<Student> studentGrid = new Grid<>();

        studentArrayList.add(new Student("Vicky", "1", 66, "Honour"));
        studentArrayList.add(new Student("Steve", "2", 45, "Pass"));
        studentArrayList.add(new Student("Ron", "3", 30, "Fail"));
        studentArrayList.add(new Student("Joe", "4", 69, "Honour"));
        studentArrayList.add(new Student("Sam", "5", 86, "Distinction"));

        studentGrid.addColumn(Student::getName).setCaption("Name");
        studentGrid.addColumn(Student::getStudentNumber).setCaption("StudentNumber");
        studentGrid.addColumn(Student::getMark).setCaption("Mark");
        studentGrid.addColumn(Student::getGrade).setCaption("Grade");

        // Set height of grid
        studentGrid.setHeightMode(HeightMode.ROW);
        studentGrid.setHeightByRows(studentArrayList.size());
        studentGrid.setWidth(100,Unit.PERCENTAGE);
        

        // Change style of grid
        UI.getCurrent().getPage().getStyles().add(
            ".my-grid-style .v-grid-body .v-grid-cell {line-height: 40px; font-size: 20px; color: red;}"
    );

        studentGrid.setStyleName("my-grid-style");

        // Select multiple items in grid
        studentGrid.setSelectionMode(SelectionMode.MULTI);

        // Add button to delete student
        studentGrid.addColumn(studentEvent -> "Delete Student",
                new ButtonRenderer<>(clickEvent -> {
                    studentArrayList.remove(clickEvent.getItem());
                    studentGrid.setItems(studentArrayList);
                }));    
                

        studentGrid.setItems(studentArrayList);

        // Trees
        Tree<String> tree = new Tree<>("Programming Technologies");
        TreeData<String> treeData = new TreeData<>();

        treeData.addItems(null, "BackEnd");
        treeData.addItems(null, "FrontEnd");

        treeData.addItem("BackEnd", "Java");
        treeData.addItem("BackEnd", "Sql");
        treeData.addItem("BackEnd", "Maven");

        treeData.addItem("FrontEnd", "Vaadin");
        treeData.addItem("FrontEnd", "HTML");
        treeData.addItem("FrontEnd", "CSS");

        tree.setDataProvider(new TreeDataProvider<>(treeData));

        tree.expand("BackEnd");

        // Left Click
        tree.addItemClickListener(event -> 
                Notification.show(event.getItem() + " clicked")
            );

        // Right click 
        tree.addContextClickListener(event ->
                Notification.show("Clicked")
        );


        // Tabsheets
        TabSheet tabSheet = new TabSheet();
        VerticalLayout tab1 = new VerticalLayout();

        Label centreLeftLabel = new Label("Centre Left Alignment");
        Label centreMiddleLabel = new Label("Centre Middle Alignment");
        Label centreRightLabel = new Label("Centre Right Alignment");

        tab1.addComponent(centreLeftLabel);
        tab1.addComponent(centreMiddleLabel);
        tab1.addComponent(centreRightLabel);

        tab1.setComponentAlignment(centreLeftLabel, Alignment.MIDDLE_LEFT);
        tab1.setComponentAlignment(centreMiddleLabel, Alignment.MIDDLE_CENTER);
        tab1.setComponentAlignment(centreRightLabel, Alignment.MIDDLE_RIGHT);

        tabSheet.addTab(tab1, "BackEnd");
        
        VerticalLayout tab2 = new VerticalLayout();
        tab2.addComponent(new Label("Label in second tab"));
        tabSheet.addTab(tab2, "FrontEnd");

        tabSheet.getTab(tab1).setClosable(true);
        tabSheet.getTab(tab2).setClosable(true);

        // GridLayout
        GridLayout gridLayout = new GridLayout(2,3);
        gridLayout.addComponent(tabSheet, 0,0);
        gridLayout.addComponent(tree, 1,0);
        gridLayout.addComponent(studentGrid, 0,1,1,1);
        gridLayout.addComponent(combo);
        
        //gridLayout.addComponent(new Button("Register"), 1,1);

        // layout.addComponent(tabSheet);
        // layout.addComponent(tree);
        // layout.addComponent(studentGrid);
        

        horizontalLayout.addComponent(excludedGroup);
        horizontalLayout.addComponent(singleGroup);
        horizontalLayout.addComponent(multiGroup);

        gridLayout.addComponent(horizontalLayout);

        layout.addComponent(chbCustomButtons);
        layout.addComponents(chbNormalButtons);


        
        layout.addComponents(label1);
        layout.addComponent(txtfield1);
        layout.addComponent(counterLabel);
        layout.addComponent(demoButton);
        
        // layout.addComponent(new Label("&nbsp",ContentMode.HTML));
        // layout.addComponents(label2);
        // Label gapLabel = new Label();
        // gapLabel.setHeight("1em");
        // layout.addComponent(gapLabel);
        // layout.addComponents(label3);
        
        
        //setContent(layout);
        gridLayout.addComponent(layout);
        setContent(gridLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
