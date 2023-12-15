package parser

import asciiConvertion.AsciiTableProvider
import commands.{BrightnessCmd, Command, CustomTableCmd, FlipCmd, InvertCmd, LoadFromFileCmd, OutputConsoleCmd, OutputFileCmd, PickAsciiTableCmd, RandomImgCmd}
import filters.FLIP_AXIS

import java.util

class ConsoleParser (cmdArgs : Seq[String]) extends Parser {

  override def parse(): Seq[Command[_]] = {
    var imageCmdsCnt = 0;
    var parsedCommands: Seq[Command[_]] = Seq.empty

    var remainingArgs = cmdArgs
    while (remainingArgs.nonEmpty) {
      remainingArgs match {
        case "--image" :: src :: tail =>
          if(imageCmdsCnt >= 1) {
            throw new IllegalArgumentException("There can be only 1 --image command.")
          }
          imageCmdsCnt += 1
          parsedCommands :+= new LoadFromFileCmd(src)
          remainingArgs = tail

        case "--image" :: tail =>
          throw new IllegalArgumentException("No src path for --image")

        case "--invert" :: tail =>
          parsedCommands :+= new InvertCmd
          remainingArgs = tail

        case "--brightness" :: value :: tail =>
          try{
            parsedCommands :+= new BrightnessCmd(value.toInt)
            remainingArgs = tail
          }catch {
            case _: NumberFormatException =>
              throw new IllegalArgumentException("Argument not convertable to Int")
          }

        case "--brightness" :: tail =>
          throw new IllegalArgumentException("No value for --brightness")

        case "--flip" :: axis :: tail =>
          if(axis.length != 1){
            throw new IllegalArgumentException("Invalid argument for --flip")
          }
          if(axis.charAt(0) == 'x'){
            parsedCommands :+= new FlipCmd(FLIP_AXIS.X)
          }
          else if (axis.charAt(0) == 'y'){
            parsedCommands :+= new FlipCmd(FLIP_AXIS.Y)
          }
          remainingArgs = tail

        case "--flip" :: tail =>
          throw new IllegalArgumentException("No axis for --flip")

        case "--output-file" :: dst :: tail =>
          parsedCommands :+= new OutputFileCmd(dst)
          remainingArgs = tail

        case "--output-file" :: tail =>
          throw new IllegalArgumentException("No dst path for --output-file")

        case "--output-console" :: tail =>
          parsedCommands :+= new OutputConsoleCmd
          remainingArgs = tail

        case "--image-random" :: tail =>
          parsedCommands :+= new RandomImgCmd()
          remainingArgs = tail

        case "--custom-table" :: table :: tail =>
          parsedCommands :+= new CustomTableCmd(AsciiTableProvider.customTable(table.toCharArray))
          remainingArgs = tail

        case "--table" :: name :: tail =>
          parsedCommands :+= new PickAsciiTableCmd(AsciiTableProvider.getTable(name))
          remainingArgs = tail

        case _ =>
          throw new IllegalArgumentException("Unexpected token: " + remainingArgs.head)
      }
    }
    parsedCommands.foreach(cmd =>{
      if(! cmd.checkArgs() ){
        throw new IllegalArgumentException("Wrong argument by " + cmd.name + " command: " + cmd.arg.getOrElse("NO ARG"))
      }
    })
    parsedCommands
  }



}
