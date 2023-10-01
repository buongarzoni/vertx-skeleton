package core

class CoreException(coreError: CoreError): RuntimeException() {
    val code = coreError.code.value
    override val message = coreError.message
}
