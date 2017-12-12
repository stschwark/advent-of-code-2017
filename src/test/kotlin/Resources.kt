fun String.asResource(work: (String) -> Unit) {
    val content = this.javaClass::class.java.getResource(this).readText()
    work(content)
}