import React from 'react';

const Home = () => {
  return (
    <div className="home-page">
      <section className="home-hero">
        <div className="home-text">
          <h1>Experienta Sunetului la un Alt Nivel</h1>
          <p>
            Bine ati venit la Modern Music Equipment Store, destinatia preferata a
            pasionatilor de sunet. De la instrumente clasice pana la cele mai noi
            tehnologii audio, oferim echipamente selectionate cu grija pentru a
            sprijini creativitatea artistilor si inginerilor de sunet. Descoperiti
            o gama variata de produse menite sa transforme orice auditie intr-o
            experienta memorabila.
          </p>
        </div>

        <div className="home-gallery">
          <img src="/img/telecaster.jpg" alt="Fender Telecaster" />
          <img src="/img/roland_keyboard.jpg" alt="Roland Keyboard" />
          <img src="/img/shure_microphone.jpg" alt="Shure Microphone" />
        </div>
      </section>

      <section className="home-features">
        <h2 className="home-section-title">Facilitati</h2>
        <div className="features-grid">
          <article className="feature-card">
            <h3>Instrumente de Top</h3>
            <p>
              Explorati o selectie vasta de instrumente si echipamente audio de
              inalta fidelitate.
            </p>
          </article>

          <article className="feature-card">
            <h3>Stoc in Timp Real</h3>
            <p>
              Verificati disponibilitatea produselor instantaneu si planificati-va
              achizitiile fara surprize.
            </p>
          </article>

          <article className="feature-card">
            <h3>Securitate Garantata</h3>
            <p>
              Adaugati produsele in cos si finalizati comanda prin metode de plata
              criptate si sigure.
            </p>
          </article>
        </div>
      </section>

      <section className="home-recommended">
        <h2 className="home-section-title">Produse Recomandate</h2>
        <div className="recommended-grid">
          <article className="product-card">
            <img src="/img/telecaster.jpg" alt="Fender Telecaster" />
            <div className="product-card-body">
              <h3>Fender Telecaster</h3>
              <p>
                Un instrument clasic, apreciat pentru claritate, versatilitate si
                expresivitate scenica.
              </p>
            </div>
          </article>

          <article className="product-card">
            <img src="/img/roland_keyboard.jpg" alt="Roland Keyboard" />
            <div className="product-card-body">
              <h3>Roland Keyboard</h3>
              <p>
                O alegere moderna pentru interpretare, compozitie si explorarea
                unor texturi sonore variate.
              </p>
            </div>
          </article>

          <article className="product-card">
            <img src="/img/shure_microphone.jpg" alt="Shure Microphone" />
            <div className="product-card-body">
              <h3>Shure Microphone</h3>
              <p>
                Ideal pentru voce si studio, oferind captare precisa si performanta
                constanta.
              </p>
            </div>
          </article>
        </div>
      </section>
    </div>
  );
};

export default Home;
