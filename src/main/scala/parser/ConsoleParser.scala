package parser

import commands.{BrightnessCmd, Command, FlipCmd, InvertCmd, LoadFromFile, OutputConsoleCmd, OutputFileCmd, RandomImgCmd}
import filters.FLIP_AXIS

import java.util

class ConsoleParser (cmdArgs : List[String]) extends Parser {

  override def parse(): List[Command[_]] = {
    var parsedCommands: List[Command[_]] = List.empty

    var remainingArgs = cmdArgs
    while (remainingArgs.nonEmpty) {
      remainingArgs match {
        case "--image" :: src :: tail =>
          parsedCommands :+= new LoadFromFile(src)
          remainingArgs = tail

        case "--invert" :: tail =>
          parsedCommands :+= new InvertCmd
          remainingArgs = tail

        case "--brightness" :: value :: tail =>
          parsedCommands :+= new BrightnessCmd(value.toInt)
          remainingArgs = tail

        case "--flip" :: axis :: tail if axis.length == 1 =>
          if(axis.length != 1){
            throw new IllegalArgumentException()
          }
          if(axis.charAt(0) == 'x'){
            parsedCommands :+= new FlipCmd(FLIP_AXIS.X)
          }
          else if (axis.charAt(0) == 'y'){
            parsedCommands :+= new FlipCmd(FLIP_AXIS.Y)
          }
          remainingArgs = tail

        case "--output-file" :: dst :: tail =>
          parsedCommands :+= new OutputFileCmd(dst)
          remainingArgs = tail

        case "--output-console" :: tail =>
          parsedCommands :+= new OutputConsoleCmd
          remainingArgs = tail

        case "--image-random" :: tail =>
          parsedCommands :+= new RandomImgCmd()
          remainingArgs = tail

        case _ =>
          // Handle unknown or invalid commands
          throw new IllegalArgumentException()
      }
    }
    parsedCommands.foreach(cmd =>{
      if(! cmd.checkArgs() ){
        throw new IllegalArgumentException("Wrong argument by " + cmd.name + " command.")
      }
    })
    parsedCommands
  }



}
