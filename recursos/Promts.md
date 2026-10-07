# Prompts utilizados - Ejercicio CardBank

Completa las dos últimas columnas después de probar cada prompt.

| # | Parte | Modo | Prompt usado | ¿Funcionó a la primera? | ¿Qué ajusté? |
|---|---:|---|---|---|---|
| 1 | 1 | Ask | `@project Explica este proyecto para alguien que está aprendiendo Java. ¿Qué hace cada clase (Tarjeta, TarjetaRepository y TarjetaController)? ¿De dónde salen los datos?` | Sí | No ajuste nada, me mostro lo necesario para entender el contexto del proyecto |
| 2 | 1 | Ask | `¿Qué significan los códigos HTTP 200, 201, 400, 404 y 409? Dame un ejemplo de cuándo usaría cada uno en esta API de tarjetas.` | Sí | No ajuste nada, da ejemplos apropiados |
| 3 | 2 | Agent | `Completa los TODO PARTE 2 de este proyecto: crea en TarjetaRepository el método buscarPorNumero(String numero), recorriendo la lista con un for y devolviendo la tarjeta o null. Crea en TarjetaController GET /api/v1/tarjetas/{numero}, con respuesta 200 si existe y 404 si no existe. No uses streams, lambdas ni Optional.` | Sí | No ajuste nada |
| 4 | 3 | Agent | `Completa los TODO PARTE 3: crea en TarjetaRepository el método guardar(Tarjeta tarjeta) y en TarjetaController POST /api/v1/tarjetas. Aplica las reglas R1 a R5 del enunciado: número de 16 caracteres, titular obligatorio, tipo CREDITO o DEBITO, límite mayor a 0 para CREDITO, número no repetido y estado ACTIVA. Devuelve 201, 400 o 409 según corresponda. Usa if simples y no uses streams.` | Sí | Da error 400 porque falta darle estado activo |
| 5 | 3 | Agent | `En el método registrarTarjeta falta aplicar la regla [R5]: Toda tarjeta nueva se registra con estado ACTIVA.. Agrégala sin cambiar el resto del código.` | Sí |  |
| 6 | 4 | Agent | `Completa el TODO PARTE 4 en TarjetaController: crea PUT /api/v1/tarjetas/{numero}/bloquear que busque la tarjeta con buscarPorNumero, cambie el estado a BLOQUEADA y responda 200 con la tarjeta, o 404 si no existe. Usa código simple y comentarios en español.` | Sí |  |
| 7 | 5 | Ask | `Revisa TarjetaController.java y TarjetaRepository.java. Indica en una tabla si se cumplen las reglas R1 a R5 y si cada endpoint devuelve los códigos correctos. Señala cualquier error o mejora sencilla.` | Sí |  |
| 8 | 5 | Ask | `Explícame paso a paso, como si fuera mi primer día programando, qué hace el método registrarTarjeta.` | Sí |  |