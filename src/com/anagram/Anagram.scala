package com.anagram

import scala.io.Source
import scala.collection.mutable.ListBuffer

/**
 * In this Object, we are taking user values and passing it to processed dictionary word Map.
 * printing all the  possible Anagram values 
 * **/
object Anagram extends App {
  
  var wordUtills = new WordUtills()
  var word_list = wordUtills.get_dictionary_list()
  val wordsmap = wordUtills.create_word_map(word_list)

  println("Please enter any word : ")
  val input_word = scala.io.StdIn.readLine()

  val output = wordsmap.get(wordUtills.sort_string(input_word))
  if (output != None) {
    println("Anagram for " + input_word + " are given below :")
    output.get.foreach(s => {
      println(s)
    })
  } else {
    println("No Anagram found for " + input_word)
  }
  
  /* wordsmap.keys.foreach{ i =>
            val word =wordsmap.get(i).getOrElse( ListBuffer[String]() )
           if (word.length > 1){
             print( "Key = " + i +" , ")
           println(" Value = " + wordsmap(i) )
           }
           }*/
   
}

/**
 * In this utill class, we are processing dictionary word and transform it to processed Map.
 * the Map contain sorted word as key and all the possible Anagram as value.
 * */
class WordUtills() {
  
  /**
   * In this method, we are sorting the string value.
   * @param value the string we want to sort
   * */
  def sort_string(value: String): String = {
    return value.toCharArray().sorted.mkString("").toLowerCase()
  }

   /**
   * This method read word.txt file and return list of all dictionary word.
   * @return list of dictionary word
   * */
  def get_dictionary_list(): ListBuffer[String] = {
    val filename = "words.txt"
    var wordList = new ListBuffer[String]()
    for (line <- Source.fromFile(filename).getLines) {
      wordList += line
    }
    return wordList
  }

  
  /**
 * In this method, we are processing dictionary word and transform it to processed Map.
 * the Map contain sorted word as key and all the possible Anagram as list of value.
 * @param word_list list of dictionary word
 * @return map of storted key and list of anagrams
 * */
  def create_word_map(word_list: ListBuffer[String]): collection.mutable.Map[String, ListBuffer[String]] = {
    var wordsmap = collection.mutable.Map[String, ListBuffer[String]]()
    for (word <- word_list) {
      var sorted_value = sort_string(word)
      if (wordsmap.get(sorted_value) == None) {
        var word_list = ListBuffer[String]()
        word_list += word
        wordsmap += (sorted_value -> word_list)
      } else {
        // println("Value already exists")
        var word_list = wordsmap.get(sorted_value).getOrElse(ListBuffer[String]())
        word_list += word
        wordsmap += (sorted_value -> word_list)
      }

    }
    return wordsmap
  }

}

