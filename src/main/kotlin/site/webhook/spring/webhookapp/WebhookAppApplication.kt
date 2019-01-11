package site.webhook.spring.webhookapp

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@SpringBootApplication
class WebhookAppApplication

fun main(args: Array<String>) {
    runApplication<WebhookAppApplication>(*args)
}

@RestController
class WebhookController {

    @PostMapping("/token", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createToken() = TokenWithMetadata(randomUUID())

    @GetMapping("/token/{token}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun readToken(@PathVariable token: String) = TokenWithMetadata(token)

    @GetMapping("/token/{token}/requests", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun readRequests(@PathVariable token: String, @RequestParam page: Int) =
            RequestResponse(listOf(), 0, 10, 0, true, 0, 0)

    private fun randomUUID() = UUID.randomUUID().toString()

    // {"premium":false,"premium_expires_at_human":null,"redirect":true,"uuid":"26a6208d-0689-4380-a8d2-f705c0f14904","ip":"46.140.42.150","user_agent":"Mozilla\/5.0 (X11; Linux x86_64) AppleWebKit\/537.36 (KHTML, like Gecko) Chrome\/71.0.3578.98 Safari\/537.36","default_content":"","default_status":200,"default_content_type":"text\/plain","timeout":null,"premium_expires_at":null,"created_at":"2019-01-09 09:50:54","updated_at":"2019-01-09 09:50:54"}

}

class TokenWithMetadata(val uuid: String)

//{
//	"data":[
//
//	],
//	"total":0,
//	"per_page":50,
//	"current_page":1,
//	"is_last_page":true,
//	"from":51,
//	"to":50
//}
class RequestResponse(val data: List<String>,
                      val total: Int,
                      val perPage: Int,
                      @field:JsonProperty("current_page")
                      val currentPage: Int,
                      @field:JsonProperty("is_last_page")
                      val isLastPage: Boolean,
                      val from: Int,
                      val to: Int)
