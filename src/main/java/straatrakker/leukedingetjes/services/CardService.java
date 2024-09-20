package straatrakker.leukedingetjes.services;

import org.springframework.stereotype.Service;
import straatrakker.leukedingetjes.domain.Card;
import straatrakker.leukedingetjes.domain.Expensiveness;
import straatrakker.leukedingetjes.repositories.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        if(card.getPrice() == null) {
            card.setPrice((float) 0);
        }

        return cardRepository.save(card);
    }

    public Optional<Card> getCard(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public List<Card> getFreeCards() {
        List<Card> cards = cardRepository.findAll();
        List<Card> freeCards = new ArrayList<>();

        for (Card card: cards) {
            if(card.isFree()) {
                freeCards.add(card);
            }
        }

        return freeCards;
    }

    public Card getRandomCard(List<Expensiveness> expensivenesses) {
        Random random = new Random();
        List<Card> cards = this.getCards();
        List<Card> results = new ArrayList<>();

        for (Card card: cards) {
            if (expensivenesses.contains(card.getExpensiveness())) {
                results.add(card);
            }
        }

        if(results.size() == 0) {
            return null;
        }

        int index = random.nextInt(results.size());
        return results.get(index);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }

    public Card updateCard(Long id, Card card) {
        Card cardToUpdate = cardRepository.findById(id).orElseThrow();
        cardToUpdate.setDescription(card.getDescription());
        return cardRepository.save(cardToUpdate);
    }
}
