package ru.ifmo.sta.baev;

import org.jfree.ui.tabbedui.AbstractTabbedUI;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static java.lang.ClassLoader.getSystemResourceAsStream;

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 * @since Aug 3, 2003
 */
public class Visualization extends JApplet {
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
    private static final String FILE_PATH = "ru/ifmo/sta/baev/visualization/vz-sample.txt";

    private JGraphModelAdapter<String, DefaultEdge> m_jgAdapter;

    public Visualization() throws HeadlessException {
    }

    public void init() {
        //delete standard menu
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.setMenuBar(null);
            frame.pack();
        }
//
//        //add my menu
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menu = new JMenu("Menu");
//        menuBar.add(menu);
//        JMenuItem item = new JMenuItem("Open...");
//        item.addActionListener(actionListener);
//        menu.add(item);
//        setJMenuBar(menuBar);



        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter<String, DefaultEdge>(g);

        JGraph jgraph = new JGraph(m_jgAdapter);

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);

        String file = "+ 1 2";
        Tree tree = null;
        try {
            tree = Parser.parse(new ByteArrayInputStream(file.getBytes()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assert tree != null;
        g.addVertex(tree.toString());
        w

//        g.addVertex("v1");
//        g.addVertex("v2");
//        g.addVertex("v3");
//        g.addVertex("v4");
//
//        g.addEdge("v1", "v2");
//        g.addEdge("v2", "v3");
//        g.addEdge("v3", "v1");
//        g.addEdge("v4", "v3");
//
//        // position vertices nicely within JGraph component
//        positionVertexAt("v1", 130, 40);
//        positionVertexAt("v2", 60, 200);
//        positionVertexAt("v3", 310, 230);
//        positionVertexAt("v4", 380, 70);

        // that's all there inputStream to it!...
    }

    private void createG(ListenableGraph<String, DefaultEdge> g, Tree tree) {
        g.addVertex(tree.toString());

    }

    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(DEFAULT_SIZE);

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }


    private void positionVertexAt(Object vertex, int x, int y) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
        Map attr = cell.getAttributes();
        Rectangle b = GraphConstants.getBounds(attr).getBounds();
        GraphConstants.setBounds( attr, new Rectangle( x, y, b.width, b.height ) );


        Map<DefaultGraphCell, Map> cellAttr = new HashMap<DefaultGraphCell, Map>();
        cellAttr.put(cell, attr);
        m_jgAdapter.edit(cellAttr, null, null, null);
    }
}