/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toc;

/**
 *
 * @author Beeravolu Singdha Reddy
 */
 import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import sun.applet.Main;


/*
Cotton Clothes should be washed at speed less than or equal to 600rmp,cannot be washed with hot water;
Wollen clothes should be washed with speed 800rpm with hot water and cannot pre_wash the clothes;
Silk clothes should be washed with speed 400rpm , cannot be washed with hot water; 
*/
public class WashingMachine {
    private static CardPanel cards;
    private static Card type1;
    private static Card type2;
    private static Card type3;
 public static String presentState = "";
 public static String previousState = "";
 public static JLabel label = new JLabel("Select Any One",SwingConstants.CENTER);
   public static int i = 0,check = 100;

    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                createAndShowGUI();             
            }
        });
        presentState = "" ;
       previousState = "Start";
    }

    public static void createAndShowGUI(){
       JFrame frame = new JFrame("Washing Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(400,150);
        frame.add(label);
        cards = new CardPanel();
        frame.getContentPane().add(new ControlPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static void createCards(){
       type1 = new Card("Cotton");
        type2 = new Card("Wollen");
        type3 = new Card("Silk");
    }
    private static final class Card extends JPanel{
        private final String name;
        public Card( final String name ){
            super();
            this.name = name;
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
        public final String getName(){
            return name;
        }
    }

    private static final class CardPanel extends JPanel{
        public CardPanel(){
            super(new CardLayout());
            createCards();
            add(type1, type1.getName());
            add(type2, type2.getName());
            add(type3, type3.getName());      
        }
    }

    private static final class ControlPanel extends JPanel{
        private static JRadioButton Button1;
        private static JRadioButton Button2;
        private static JRadioButton Button3;
        private static JButton Button;

        public ControlPanel(){
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            add(createJRadioButtonPanel());
            add(createJButtonPanel());
        }

        private final JPanel createJRadioButtonPanel(){
            final JPanel panel = new JPanel();
            
            Button1 = new JRadioButton(type1.getName());
            Button2 = new JRadioButton(type2.getName());
            Button3 = new JRadioButton(type3.getName());
            ButtonGroup group = new ButtonGroup();
            group.add(Button1);
            group.add(Button2);
            group.add(Button3);
            panel.add(Button1);
            panel.add(Button2);
            panel.add(Button3);
            
            return panel;
        }

        private final JPanel createJButtonPanel(){
            final JPanel panel = new JPanel();
            Button = new JButton("Next");
            Button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    CardLayout cl = (CardLayout) cards.getLayout();
                
              
         for(i=0;i<100;i++){
                 if(presentState == ""){
                   if(Button1.isSelected()){
                        presentState = "First State" ;
                        label.setText("Cotton Dresses");
                   }
                  else if(Button2.isSelected()){                        
                        presentState = "Second State" ;
                        label.setText("Wollen Dresses");
                    }
                    else if(Button3.isSelected()){
                        presentState = "Third State" ; 
                   label.setText("Silk Dresses");
                    }
                 }
               if(presentState == "First State" || presentState == "Second State" || presentState == "Third State"){
                  if(check == 100){     
                   //   System.out.println("hello dear");
                    Button1.setText("400rpm"); 
                    Button2.setText("600rpm");
                    Button3.setText("800rpm");
                    Button.setText("Level 1");
                    check = check + 2 ;
                     break;
                 }
             
                    if(presentState == "First State" && Button3.isSelected() ){                       
                       check = 5; 
                       label.setText("800rpm is not allowed");
                    }
                    
                    else if(presentState == "First State" && (Button2.isSelected() )){
                     
                         previousState = presentState;
                        presentState = "Fourth State";
                        label.setText("600 rpm");
                        check++;
               
                    }
                    else if(presentState == "First State" && (Button1.isSelected() )){
                         //System.out.println("first state first button");
                         previousState = presentState;
                        presentState = "Fourth State";
                       // System.out.println("hey");
                        label.setText("cotton dresses at 400 rpm");
                        check++;
                    }
                    else if(presentState == "Second State" && Button3.isSelected()){
                       // System.out.println("wollen 800rpm");
                        previousState = presentState;
                        presentState = "Seventh State";
                        label.setText("Wollen Dresses at 800 rpm");
                        check++;
                     
                    }
                    else if(presentState == "Second State" && (Button2.isSelected()|| Button1.isSelected())){
                        previousState = presentState;
                       check = 5;
                       label.setText("This is not allowed select another ");
                    }
                    else if(presentState == "Third State" && Button1.isSelected()){
                         previousState = presentState;
                        presentState = "Eighth State"; 
                        label.setText("Silk Dresses at 400rpm");
                        check++;
                    
                    }
                    else if(presentState == "Third State" && (Button2.isSelected()|| Button3.isSelected())){                        
                         previousState = presentState;
                       check =5;
                       label.setText("This is not allowed select another");
                  }
                   if(check >= 104)
                    {
                      check = 50;                         
                        break;
                    }
                    check = 50; 
               }
               
          
                   if(presentState == "Fourth State" || presentState == "Seventh State" || presentState == "Eighth State"){
                     
                       if(check == 50){
                           Button1.setText("Hot Water");
                     Button2.setText("Cold");
                    Button3.setText("Warm"); 
                   Button.setText("Level 2");
                   check = check +2;
                   break;
                       }
                    
                    if(presentState == "Fourth State" && (Button2.isSelected() || Button3.isSelected()) ) {
                       //System.out.println("hahaah");
                        label.setText("Cotton Dresses");
                        check ++;
                         previousState = presentState;
                         presentState = "Elventh State";
                        
                    }
                    else if(presentState == "Fourth State" && (Button1.isSelected())){
                         check = 30;
                         label.setText("This is not allowed select another");
                    }
                    else if(presentState == "Seventh State" && (Button1.isSelected())){
                        previousState = presentState;
                         presentState = "Twelth State";
                         check++;
                         label.setText("Wollen Dresses");
                    }
                    else if (presentState == "Seventh State" && (Button3.isSelected() ||Button2.isSelected())){
                        check = 30;
                        label.setText("This is not allowed select another");
                    }
                    else if(presentState == "Eighth State" && (Button1.isSelected())){
                       check = 30;
                        label.setText("Silk clothes cannot be washed with hot water");
                   }
                    else if( presentState == "Eighth State" && (Button2.isSelected() ||Button3.isSelected())){
                        check++;
                        previousState = presentState;
                        presentState = "Fifteenth State";
                        label.setText("Silk Clothes washing");
                        
                   }
                    if(check >= 54){
                        check = 20;
                        break;                   
                    }
                    check = 20;
                   }
                   else if(presentState == "Fifth State"  ||  presentState == "Sixth State" || presentState == "Ninth State"){
                       label.setText("This is not allowed select another");
                       break;
                    } 
                   if(presentState == "Elventh State" || presentState == "Twelth State" || presentState == "Fifteenth State"){
                       if(check == 20){
                       Button1.setText("Pre Wash"); 
                    Button2.setText("Wash");
                   Button3.setText("Dry");
                    Button.setText("Level 3");
                    check = check +2;
                    break;
                      }
                    if(presentState == "Elventh State" && (Button2.isSelected() || Button3.isSelected() || Button1.isSelected()) ) {
                        previousState = presentState;
                        presentState = "Seventeenth State";
                        check++;
                        label.setText("Cotton Dresses");
                    }
                 
                    else if(presentState == "Twelth State" && (Button1.isSelected())){
                        check = 10;
                        label.setText("Wollen clothes cannot be pre-washed");
                    }
                    else if (presentState == "Twelth State" && (Button3.isSelected() ||Button2.isSelected())){
                        previousState = presentState;
                        presentState = "Eighteenth State";
                        check++;
                        label.setText("Wollen Dresses");
                    }
          
                    else if( presentState == "Fifteenth State" && (Button3.isSelected() ||Button2.isSelected() || Button1.isSelected())){
                       previousState = presentState;
                       presentState = "Nineteenth State";
                       check++;
                       label.setText("Silk Clothes");
                   }
                    if( check >= 24)
                        break;
                   }
                   if(presentState == "Seventeenth State" || presentState == "Eighteenth State" || presentState == "Nineteenth State"){
                       label.setText("THANK YOU, wait for a while");
                       System.exit(0);
                   }
                }
                }
            });  
            panel.add(Button);
            return panel;
        }
    }
}
