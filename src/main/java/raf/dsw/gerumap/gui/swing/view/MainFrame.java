package raf.dsw.gerumap.gui.swing.view;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.controller.ActionManager;
import raf.dsw.gerumap.gui.swing.tree.MapTree;
import raf.dsw.gerumap.gui.swing.tree.MapTreeImplementation;
import raf.dsw.gerumap.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.mapRepository.view.ProjectView;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private ActionManager actionManager;
    private MyToolBar toolBar;
    private ProjectView mainWindow;
    private MapTree mapTree;
    private MapTreeView projectExplorer;
    @Getter(AccessLevel.NONE)
    private MyMenuBar menuBar;



    private MainFrame(){

    }
    private void initialise(){
        initActionManager();
        initialiseTree();
        initialiseGUI();
    }

    private void initialiseTree(){
        mapTree = new MapTreeImplementation();
        projectExplorer = mapTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer());
    }

    private void initialiseGUI() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setMinimumSize(new Dimension(950,550));
        setSize(screenWidth/2,screenHeight/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("GeRuMap");
        setLocationRelativeTo(null);


        menuBar = new MyMenuBar();
        setJMenuBar(menuBar);

        toolBar = new MyToolBar();
        add(toolBar,BorderLayout.NORTH);


        mainWindow = new ProjectView();
        mainWindow.setBackground(Color.lightGray);
        JScrollPane treeScroll = new JScrollPane(projectExplorer);
        treeScroll.setMinimumSize(new Dimension(200,150));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treeScroll,mainWindow);
        getContentPane().add(splitPane,BorderLayout.CENTER);
        splitPane.setDividerLocation(250);

        //this.repaint();

    }

    private void initActionManager() {
        actionManager = new ActionManager();
    }

    public static MainFrame getInstance() {
        if(instance == null){
            instance = new MainFrame();
            instance.initActionManager();
            instance.initialise();
        }
        return instance;
    }




    public MyToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(MyToolBar toolBar) {
        this.toolBar = toolBar;
    }




    public MapTreeView getProjectExplorer() {
        return projectExplorer;
    }


}
