package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class TLSSocketFactoryTestMocks {

    @Test
    public void preparedSocket_NullProtocols() {

        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = mock(SSLSocket.class);
        when(socket.getSupportedProtocols()).thenReturn(null);

        when(socket.getEnabledProtocols()).thenReturn(null);
        f.prepareSocket(socket);

        // On vérifie que setEnabledProtocols n'est pas appelé
        verify(socket, never()).setEnabledProtocols(any());
    }

    @Test
    public void typical() {
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = mock(SSLSocket.class);

        // On mélange les protocols
        String[] supported = new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};

        String[] enabled = new String[]{"SSLv3", "TLSv1"};

        when(socket.getSupportedProtocols()).thenReturn(supported);
        when(socket.getEnabledProtocols()).thenReturn(enabled);

        f.prepareSocket(socket);

        // ON Vérifier que setEnabledProtocols a été appelé avec le bon tableau

        verify(socket).setEnabledProtocols(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3"});
    }

    // Pour couvrir le cas où le code de prepareSocket serait vide,
    // on va ajouter un test qui force une certaine logique.
    @Test
    public void testIfNoChangeInPrepareSocket() {
        // On va créer un scenario où le code vide ne va rien changer,
        // alors qu' on s'attend à un changement dans les protocoles.
        TLSSocketFactory f = new TLSSocketFactory();
        SSLSocket socket = mock(SSLSocket.class);

        when(socket.getSupportedProtocols()).thenReturn(new String[]{"TLSv1", "TLSv1.1"});
        when(socket.getEnabledProtocols()).thenReturn(new String[]{"TLSv1"});

        f.prepareSocket(socket);

        // On veut s'assurer que setEnabledProtocols soit appelé avec un certain résultat
        // Si le code est vide, ce test va echouer , en signalant le problème.

        verify(socket).setEnabledProtocols(new String[]{"TLSv1.1", "TLSv1"});
    }
}
