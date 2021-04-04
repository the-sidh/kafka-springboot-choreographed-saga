package br.com.fiap.utils

inline fun <reified R> invokePrivateFunction(
    callingCLass: Any,
    functionName: String,
    parameters: Array<Any>
): R? {
    val parametersClasses =
        parameters.map { type ->
            when (type) {
                is Int -> Integer.TYPE
                is Float -> java.lang.Float.TYPE
                is Double -> java.lang.Double.TYPE
                is Short -> java.lang.Short.TYPE
                is Boolean -> java.lang.Boolean.TYPE
                else -> type::class.java
            }
        }.toTypedArray()

    val function =
        callingCLass.javaClass.getDeclaredMethod(functionName, *parametersClasses)
    function.isAccessible = true
    return function.invoke(callingCLass, *parameters) as R?
}
