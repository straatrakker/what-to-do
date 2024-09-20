package straatrakker.leukedingetjes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import straatrakker.leukedingetjes.domain.Card;
import straatrakker.leukedingetjes.domain.Expensiveness;
import straatrakker.leukedingetjes.services.CardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {

    private CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards(@RequestParam(required = false) Boolean free) {
        if (free != null && free) {
            return ResponseEntity.ok(cardService.getFreeCards());
        }
        return ResponseEntity.ok(cardService.getCards());
    }


    @GetMapping("/pick")
    public ResponseEntity<Card> getRandomCard(@RequestParam(required = false) List<Expensiveness> expensiveness) {

        return ResponseEntity.ok(cardService.getRandomCard(expensiveness));
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        if(card.getDescription() == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(cardService.createCard(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCardById(@PathVariable Long id, @RequestBody Card card) {

        cardService.updateCard(id, card);
        card.setId(id);

        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardById(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
