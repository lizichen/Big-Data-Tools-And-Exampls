import scala.xml._

val data = sc.wholeTextFiles("/user/lc3397/loudacre/activations/*")
val xml_content_rdd = data.map(s => s._2)

def getResultString(xml: String):Seq[String] = {
  val nodes = XML.loadString(xml)
  (nodes \\ "account-number").zip(nodes \\ "model").map(zipped => (zipped._1.text + ":" + zipped._2.text))
}

val results_string_rdd = xml_content_rdd.map(xml_string => getResultString(xml_string))

results_string_rdd.take(1).foreach(println)

results_string_rdd.saveAsTextFile("loudacre/activations/account-models")



// =======================
val data = sc.wholeTextFiles("/user/lc3397/loudacre/activations/*")
val xml_content_rdd = data.map(s => s._2)

def getactivations(xmlstring: String) :List[Node] = {
  val nodes = XML.loadString(xmlstring)
  nodes.toList
}

val xml_iter = xml_content_rdd.map(s => getactivations(s))

def getmodel(activation: Node): List[Node] = {
	val many_model = (activation \ "activation" \ "model")
	many_model.toList
}

def getmodel(activation: Node): List[String] = {
val many_model = (activation \ "activation" \ "model")
val many_model_string = many_model.map(s => s.text)
many_model_string.toList
}

// =======================

def getaccount(activation: Node): List[Node] = {
	val many_account = (activation \ "activation" \ "account-number")
	many_account.toList
}