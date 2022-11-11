package sheraz.com

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sheraz.com.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun buClick(view: View) {

        val buSelected:Button = view as Button
        var cellId=0
        when(buSelected.id)
        {
            R.id.b11 -> cellId=1
            R.id.b12 -> cellId=2
            R.id.b13 -> cellId=3

            R.id.b21 -> cellId=4
            R.id.b22 -> cellId=5
            R.id.b23 -> cellId=6

            R.id.b31 -> cellId=7
            R.id.b32 -> cellId=8
            R.id.b33 -> cellId=9

        }
        //Toast.makeText(this,"ID: $cellId",Toast.LENGTH_SHORT).show()
        PlayGame(cellId,buSelected)
        checkWinner()
    }

    fun autoPlay()
    {
        var emptyCell = ArrayList<Int>()

        for (cellId in 1..9)
        {
            if(!player1.contains(cellId) && !player2.contains(cellId))
            {
                emptyCell.add(cellId)
            }
        }




        if(!emptyCell.isEmpty())
        {
            val r= Random()
            val randIndex =r.nextInt(emptyCell.size)
            val cellId=emptyCell[randIndex]
            var buSelected:Button?
            buSelected = when(cellId){
                1 -> binding.b11
                2 -> binding.b12
                3 -> binding.b13
                4 -> binding.b21
                5 -> binding.b22
                6 -> binding.b23
                7 -> binding.b31
                8 -> binding.b32
                9 -> binding.b33
                else ->
                    binding.b11
            }
            PlayGame(cellId,buSelected)
        }else
        {
            countDraw++
            Toast.makeText(this,"game is draw", Toast.LENGTH_SHORT).show()
            ClearAll();       }




     }

    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    var activeP=1

    private fun PlayGame(cellId: Int, buSelected: Button) {

        if(activeP==1)
        {
            buSelected.text="x"
            buSelected.setBackgroundColor(Color.GREEN)
            player1.add(cellId)
            activeP=2
            buSelected.isEnabled=false
            autoPlay()
        }
        else
        {

            buSelected.text="O"
            buSelected.setBackgroundColor(Color.BLUE)
            player2.add(cellId)
            activeP=1
            buSelected.isEnabled=false
        }


    }
    fun checkWinner(){
        var winer=-1

        // row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winer=1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winer=2
        }
        // row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winer=1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winer=2
        }
        // row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winer=1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winer=2
        }


        // col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winer=1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winer=2
        }
        // col 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winer=1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winer=2
        }
        // col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winer=1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winer=2
        }


        if( winer != -1){

            when(winer) {
                1 ->{
                    countP1win++
                    Toast.makeText(this," Player 1  win the game", Toast.LENGTH_SHORT).show()
//                    DisableAfterWin()
                    ClearAll()
                }
                2->{
                    countP2win++
                    Toast.makeText(this," Player 2  win the game", Toast.LENGTH_SHORT).show()
//                    DisableAfterWin()
                    ClearAll()

                }
                else->{
                    countDraw++
                    Toast.makeText(this,"game is draw", Toast.LENGTH_SHORT).show()
                    ClearAll()


                }
            }

           /* if (winer==1){

            }
            else if(winer == 2){

            }
            else
            {

            }*/

        }
    }
    fun DisableAfterWin()
    {
        for(i in 1..3)
        {
            for(j in 1..3)
            {
                try{
                    val id:Int = resources.getIdentifier("b$i$j", "id", this.packageName)


                    val button = findViewById<Button>(id)

                    button.isEnabled=false

                }catch (exception: Exception)
                {
                    Toast.makeText(this,"Error in button",Toast.LENGTH_SHORT)
                }
            }


        }
    }
    var countP1win=0
    var countP2win=0
    var countDraw=0

    fun ClearAll()
    {
        activeP=1
        player1.clear()
        player2.clear()

        for(i in 1..3)
        {
            for(j in 1..3)
            {
                try{
                    val id:Int = resources.getIdentifier("b$i$j", "id", this.packageName)


                    val button = findViewById<Button>(id)

                    button.isEnabled=true
                    button.setBackgroundColor(Color.WHITE)
                    button.text=""

                }catch (_: Exception)
                {
                }
            }
        }
        Toast.makeText(this,"P1 wins:$countP1win, P1 wins:$countP2win, Draws: {$countDraw}",Toast.LENGTH_SHORT).show()


    }

}