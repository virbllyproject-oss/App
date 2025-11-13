import { useState, useEffect } from 'react'
import './App.css'

function App() {
  const [currentSlide, setCurrentSlide] = useState(0)
  const [direction, setDirection] = useState('right')

  const slides = [
    {
      id: 0,
      title: "FinanceApp",
      subtitle: "Twoja przyszłość finansowa zaczyna się tutaj",
      content: "Nowoczesna platforma do zarządzania finansami osobistymi",
      gradient: "from-blue-600 via-purple-600 to-pink-600",
      animation: "fade-in"
    },
    {
      id: 1,
      title: "O Aplikacji",
      subtitle: "Kompleksowe rozwiązanie finansowe",
      content: "Zarządzaj swoimi finansami w jednym miejscu. Śledź wydatki, planuj budżet i osiągaj cele finansowe.",
      features: [
        "📊 Analiza wydatków w czasie rzeczywistym",
        "💰 Inteligentne planowanie budżetu",
        "📈 Śledzenie inwestycji",
        "🎯 Cele oszczędnościowe"
      ],
      gradient: "from-emerald-600 via-teal-600 to-cyan-600",
      animation: "slide-left"
    },
    {
      id: 2,
      title: "Kluczowe Funkcje",
      subtitle: "Wszystko czego potrzebujesz",
      features: [
        {
          icon: "💳",
          title: "Zarządzanie Kartami",
          desc: "Wszystkie karty w jednym miejscu"
        },
        {
          icon: "📱",
          title: "Mobilny Dostęp",
          desc: "Zarządzaj finansami z każdego miejsca"
        },
        {
          icon: "🔔",
          title: "Powiadomienia",
          desc: "Bądź na bieżąco z wydatkami"
        },
        {
          icon: "📊",
          title: "Raporty",
          desc: "Szczegółowe analizy finansowe"
        }
      ],
      gradient: "from-violet-600 via-purple-600 to-fuchsia-600",
      animation: "scale-in"
    },
    {
      id: 3,
      title: "Statystyki",
      subtitle: "Liczby mówią same za siebie",
      stats: [
        { value: "50K+", label: "Aktywnych Użytkowników" },
        { value: "€2M+", label: "Zarządzanych Środków" },
        { value: "99.9%", label: "Dostępność Systemu" },
        { value: "4.8★", label: "Ocena Aplikacji" }
      ],
      gradient: "from-amber-600 via-orange-600 to-red-600",
      animation: "slide-up"
    },
    {
      id: 4,
      title: "Bezpieczeństwo",
      subtitle: "Twoje dane są bezpieczne",
      content: "Wykorzystujemy najnowsze technologie zabezpieczeń, aby chronić Twoje finanse",
      features: [
        "🔒 Szyfrowanie end-to-end",
        "🛡️ Uwierzytelnianie dwuskładnikowe",
        "🔐 Biometryczne logowanie",
        "✅ Zgodność z RODO"
      ],
      gradient: "from-green-600 via-emerald-600 to-teal-600",
      animation: "slide-right"
    },
    {
      id: 5,
      title: "Zacznij Już Dziś",
      subtitle: "Twoja finansowa przyszłość czeka",
      content: "Dołącz do tysięcy zadowolonych użytkowników i przejmij kontrolę nad swoimi finansami",
      cta: "Rozpocznij Bezpłatnie",
      gradient: "from-indigo-600 via-blue-600 to-cyan-600",
      animation: "fade-in"
    }
  ]

  const totalSlides = slides.length

  const nextSlide = () => {
    setDirection('right')
    setCurrentSlide((prev) => (prev + 1) % totalSlides)
  }

  const prevSlide = () => {
    setDirection('left')
    setCurrentSlide((prev) => (prev - 1 + totalSlides) % totalSlides)
  }

  const goToSlide = (index) => {
    setDirection(index > currentSlide ? 'right' : 'left')
    setCurrentSlide(index)
  }

  useEffect(() => {
    const handleKeyDown = (e) => {
      if (e.key === 'ArrowRight') nextSlide()
      if (e.key === 'ArrowLeft') prevSlide()
    }
    window.addEventListener('keydown', handleKeyDown)
    return () => window.removeEventListener('keydown', handleKeyDown)
  }, [currentSlide])

  const slide = slides[currentSlide]

  return (
    <div className="min-h-screen w-full overflow-hidden bg-gray-900">
      {/* Animated Background */}
      <div className={`fixed inset-0 bg-gradient-to-br ${slide.gradient} opacity-20 transition-all duration-1000`}></div>
      
      {/* Slide Content */}
      <div className="relative z-10 min-h-screen flex items-center justify-center p-8">
        <div 
          key={currentSlide}
          className={`w-full max-w-6xl animate-${slide.animation}`}
        >
          {/* Slide 0 - Title Slide */}
          {slide.id === 0 && (
            <div className="text-center space-y-8">
              <div className="space-y-4">
                <h1 className={`text-8xl font-bold text-gradient ${slide.gradient} animate-float`}>
                  {slide.title}
                </h1>
                <p className="text-3xl text-gray-300 animate-fade-in" style={{animationDelay: '0.3s'}}>
                  {slide.subtitle}
                </p>
              </div>
              <p className="text-xl text-gray-400 animate-fade-in" style={{animationDelay: '0.6s'}}>
                {slide.content}
              </p>
              <div className="pt-8 animate-fade-in" style={{animationDelay: '0.9s'}}>
                <div className="inline-block px-8 py-4 bg-gradient-to-r from-blue-600 to-purple-600 rounded-full text-xl font-semibold hover:scale-110 transition-transform cursor-pointer">
                  Poznaj więcej →
                </div>
              </div>
            </div>
          )}

          {/* Slide 1 - About */}
          {slide.id === 1 && (
            <div className="space-y-12">
              <div className="text-center space-y-4">
                <h2 className={`text-6xl font-bold text-gradient ${slide.gradient}`}>
                  {slide.title}
                </h2>
                <p className="text-2xl text-gray-300">{slide.subtitle}</p>
              </div>
              <p className="text-xl text-gray-400 text-center max-w-3xl mx-auto">
                {slide.content}
              </p>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto">
                {slide.features.map((feature, idx) => (
                  <div 
                    key={idx}
                    className="bg-gray-800/50 backdrop-blur-sm p-6 rounded-2xl border border-gray-700 hover:border-emerald-500 transition-all hover:scale-105 animate-fade-in"
                    style={{animationDelay: `${idx * 0.1}s`}}
                  >
                    <p className="text-xl">{feature}</p>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Slide 2 - Features */}
          {slide.id === 2 && (
            <div className="space-y-12">
              <div className="text-center space-y-4">
                <h2 className={`text-6xl font-bold text-gradient ${slide.gradient}`}>
                  {slide.title}
                </h2>
                <p className="text-2xl text-gray-300">{slide.subtitle}</p>
              </div>
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                {slide.features.map((feature, idx) => (
                  <div 
                    key={idx}
                    className="bg-gray-800/50 backdrop-blur-sm p-8 rounded-2xl border border-gray-700 hover:border-purple-500 transition-all hover:scale-110 text-center space-y-4 animate-scale-in"
                    style={{animationDelay: `${idx * 0.15}s`}}
                  >
                    <div className="text-6xl animate-float" style={{animationDelay: `${idx * 0.2}s`}}>
                      {feature.icon}
                    </div>
                    <h3 className="text-xl font-bold">{feature.title}</h3>
                    <p className="text-gray-400">{feature.desc}</p>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Slide 3 - Stats */}
          {slide.id === 3 && (
            <div className="space-y-12">
              <div className="text-center space-y-4">
                <h2 className={`text-6xl font-bold text-gradient ${slide.gradient}`}>
                  {slide.title}
                </h2>
                <p className="text-2xl text-gray-300">{slide.subtitle}</p>
              </div>
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                {slide.stats.map((stat, idx) => (
                  <div 
                    key={idx}
                    className="bg-gray-800/50 backdrop-blur-sm p-10 rounded-2xl border border-gray-700 hover:border-orange-500 transition-all hover:scale-110 text-center space-y-2 animate-slide-up"
                    style={{animationDelay: `${idx * 0.1}s`}}
                  >
                    <div className={`text-5xl font-bold text-gradient ${slide.gradient}`}>
                      {stat.value}
                    </div>
                    <p className="text-gray-400 text-lg">{stat.label}</p>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Slide 4 - Security */}
          {slide.id === 4 && (
            <div className="space-y-12">
              <div className="text-center space-y-4">
                <h2 className={`text-6xl font-bold text-gradient ${slide.gradient}`}>
                  {slide.title}
                </h2>
                <p className="text-2xl text-gray-300">{slide.subtitle}</p>
              </div>
              <p className="text-xl text-gray-400 text-center max-w-3xl mx-auto">
                {slide.content}
              </p>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto">
                {slide.features.map((feature, idx) => (
                  <div 
                    key={idx}
                    className="bg-gray-800/50 backdrop-blur-sm p-6 rounded-2xl border border-gray-700 hover:border-green-500 transition-all hover:scale-105 animate-fade-in"
                    style={{animationDelay: `${idx * 0.1}s`}}
                  >
                    <p className="text-xl">{feature}</p>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* Slide 5 - CTA */}
          {slide.id === 5 && (
            <div className="text-center space-y-12">
              <div className="space-y-4">
                <h2 className={`text-7xl font-bold text-gradient ${slide.gradient} animate-float`}>
                  {slide.title}
                </h2>
                <p className="text-3xl text-gray-300 animate-fade-in" style={{animationDelay: '0.3s'}}>
                  {slide.subtitle}
                </p>
              </div>
              <p className="text-xl text-gray-400 max-w-3xl mx-auto animate-fade-in" style={{animationDelay: '0.6s'}}>
                {slide.content}
              </p>
              <div className="pt-8 space-y-6 animate-fade-in" style={{animationDelay: '0.9s'}}>
                <div className="inline-block px-12 py-6 bg-gradient-to-r from-indigo-600 to-blue-600 rounded-full text-2xl font-bold hover:scale-110 transition-transform cursor-pointer shadow-2xl">
                  {slide.cta}
                </div>
                <p className="text-gray-500">Bez karty kredytowej • Anuluj w każdej chwili</p>
              </div>
            </div>
          )}
        </div>
      </div>

      {/* Navigation Controls */}
      <div className="fixed bottom-8 left-0 right-0 z-20">
        <div className="flex items-center justify-center gap-8">
          {/* Previous Button */}
          <button
            onClick={prevSlide}
            className="bg-gray-800/80 backdrop-blur-sm hover:bg-gray-700 p-4 rounded-full transition-all hover:scale-110 border border-gray-600"
            aria-label="Previous slide"
          >
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15 19l-7-7 7-7" />
            </svg>
          </button>

          {/* Slide Indicators */}
          <div className="flex gap-3">
            {slides.map((_, idx) => (
              <button
                key={idx}
                onClick={() => goToSlide(idx)}
                className={`transition-all rounded-full ${
                  idx === currentSlide
                    ? 'w-12 h-3 bg-gradient-to-r ' + slide.gradient
                    : 'w-3 h-3 bg-gray-600 hover:bg-gray-500'
                }`}
                aria-label={`Go to slide ${idx + 1}`}
              />
            ))}
          </div>

          {/* Next Button */}
          <button
            onClick={nextSlide}
            className="bg-gray-800/80 backdrop-blur-sm hover:bg-gray-700 p-4 rounded-full transition-all hover:scale-110 border border-gray-600"
            aria-label="Next slide"
          >
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5l7 7-7 7" />
            </svg>
          </button>
        </div>

        {/* Slide Counter */}
        <div className="text-center mt-4 text-gray-400">
          {currentSlide + 1} / {totalSlides}
        </div>
      </div>

      {/* Keyboard Hint */}
      <div className="fixed top-8 right-8 text-gray-500 text-sm bg-gray-800/50 backdrop-blur-sm px-4 py-2 rounded-lg border border-gray-700">
        Użyj strzałek ← → do nawigacji
      </div>
    </div>
  )
}

export default App
