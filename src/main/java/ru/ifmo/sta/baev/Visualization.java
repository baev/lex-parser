package ru.ifmo.sta.baev;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 06.04.13
 */
public class Visualization extends JApplet {
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

    private static final int X_OFFSET = 70;
    private static final int Y_OFFSET = 70;

    private int MAX_X_OFFSET = 0;
    private int MAX_Y_OFFSET = 0;

    private JGraphModelAdapter<Tree, DefaultEdge> m_jgAdapter;
    Tree tree4visualization;

    public Visualization() throws HeadlessException {
        System.out.println("Creating visualizer...");

        String expression = "+ - 1 * 2 3 4";

        try {
            tree4visualization = Parser.parse(new ByteArrayInputStream(expression.getBytes()));
        } catch (ParseException e) {
            throw new HeadlessException("Initialisation problems: " + e.getMessage());
        }
    }

    public void init() {
        System.out.println("Applet initializing...");

        //delete standard menu
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            frame.setMenuBar(null);
            frame.pack();
        }

        ListenableGraph<Tree, DefaultEdge> graph = new ListenableDirectedGraph<Tree, DefaultEdge>(DefaultEdge.class);
        m_jgAdapter = new JGraphModelAdapter<Tree, DefaultEdge>(graph);
        JGraph jgraph = new JGraph(m_jgAdapter);

        resize(DEFAULT_SIZE);

        createGraphFromTree(graph, tree4visualization, null, 0, 1);

        MAX_X_OFFSET += (100 + X_OFFSET);
        MAX_Y_OFFSET += Y_OFFSET;

        adjustDisplaySettings(jgraph);

        getContentPane().add(new JScrollPane(jgraph, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    }

    private int createGraphFromTree(ListenableGraph<Tree, DefaultEdge> graph, Tree tree,
                                    Tree parent, int parentXOffset, int parentYOffset) {

        graph.addVertex(tree);
        if (parent != null) {
            graph.addEdge(parent, tree);
        }

        if (tree.getToken().equals(Token.END) || tree.getToken().equals(Token.OPERAND)) {
            positionVertexAt(tree, (1 + parentXOffset) * X_OFFSET, parentYOffset * Y_OFFSET);
            return 1;
        }

        Tree l = tree.getLeft();
        Tree r = tree.getRight();

        int offsetLeft = createGraphFromTree(graph, l, tree, parentXOffset, parentYOffset + 1);
        int offsetRight = createGraphFromTree(graph, r, tree, offsetLeft + parentXOffset + 1, parentYOffset + 1);

        positionVertexAt(tree, (offsetLeft + parentXOffset + 1) * X_OFFSET, parentYOffset * Y_OFFSET);

        return offsetLeft + offsetRight + 1;
    }

    private void adjustDisplaySettings(JGraph jg) {
        jg.setPreferredSize(new Dimension(MAX_X_OFFSET, MAX_Y_OFFSET));

        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception ignored) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }


    private void positionVertexAt(Object vertex, int x, int y) {
        if (MAX_X_OFFSET < x) {
            MAX_X_OFFSET = x;
        }
        if (MAX_Y_OFFSET < y) {
            MAX_Y_OFFSET = y;
        }

        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
        Map attr = cell.getAttributes();
        Rectangle b = GraphConstants.getBounds(attr).getBounds();
        GraphConstants.setBounds(attr, new Rectangle(x, y, b.width, b.height));


        Map<DefaultGraphCell, Map> cellAttr = new HashMap<DefaultGraphCell, Map>();
        cellAttr.put(cell, attr);
        m_jgAdapter.edit(cellAttr, null, null, null);
    }
}